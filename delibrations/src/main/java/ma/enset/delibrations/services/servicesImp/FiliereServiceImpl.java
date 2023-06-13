package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.FiliereMapper;
import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.entities.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.entities.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.services.FiliereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
    generateCode()
    createFiliere()
    updateFiliere()
    deleteFiliere()
 */
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class FiliereServiceImpl implements FiliereService {
    private FiliereRepository filiereRepository;
    private FiliereMapper filiereMapper;
    private DepartementRepository departementRepository;

    private RegleCalculRepository regleCalculRepository;
    private ProfesseurRepository professeurRepository;
    private ElementRepository elementRepository;
    private ModuleRepository moduleRepository;
    private SemestreRepository semestreRepository;

    private String generateCode() {
        //TODO: Generate Filiere code based on the format TI00006
        return "CODE" + System.currentTimeMillis();
    }


    @Override
    public FiliereResponseDTO createFiliere(FiliereRequestDTO filiereRequestDTO) throws RegleCalculNotFoundException {
        if (filiereRequestDTO.getCode() == null) filiereRequestDTO.setCode(generateCode());
        else {
            Filiere filiere = filiereRepository.findByCode(filiereRequestDTO.getCode());
            if (filiere != null)
                throw new RuntimeException("Filiere with code " + filiereRequestDTO.getCode() + " already exists");
        }
        filiereRequestDTO.setCode(generateCode());

        Filiere savedFiliere = filiereRepository.save(filiereMapper.fromRequestDTOtoEntity(filiereRequestDTO));

        if (filiereRequestDTO.getRegleCalculId() != null) {
            RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(filiereRequestDTO.getRegleCalculId());
            if (regleCalcul != null) savedFiliere.setRegleCalcul(regleCalcul);
            else throw new RegleCalculNotFoundException(filiereRequestDTO.getRegleCalculId());
        }
        if(filiereRequestDTO.getDepartementId()!=null){
            Departement departement = departementRepository.findById(filiereRequestDTO.getDepartementId()).orElse(null);
            savedFiliere.setDepartement(departement);
        }
        filiereRepository.save(savedFiliere);

        FiliereResponseDTO filiereResponseDTO = filiereMapper.fromEntityToResponseDTO(savedFiliere);
        //filiereResponseDTO.setDepartementId(savedFiliere.getDepartement().getId());
        filiereResponseDTO.setDepartementIntitule(savedFiliere.getDepartement().getIntitule());

        if (savedFiliere.getRegleCalcul() != null)
            filiereResponseDTO.setRegleCalculId(savedFiliere.getRegleCalcul().getId());
        return filiereResponseDTO;

    }

    @Override
    public FiliereResponseDTO updateFiliere(FiliereRequestDTO filiereRequestDTO) throws FiliereNotFoundException, DepartementNotFoundException, RegleCalculNotFoundException {
        if (filiereRequestDTO.getCode() == null) throw new FiliereNotFoundException("Filiere code is required");
        else {
            Filiere filiere = filiereRepository.findByCode(filiereRequestDTO.getCode());
            if (filiere == null) throw new FiliereNotFoundException(filiereRequestDTO.getCode());
            if (filiereRequestDTO.getIntitule() != null) filiere.setIntitule(filiereRequestDTO.getIntitule());
            if (filiereRequestDTO.getDepartementId() != null) {
                Departement departement = departementRepository.findById(filiereRequestDTO.getDepartementId()).orElseThrow(() -> new DepartementNotFoundException(filiereRequestDTO.getDepartementId()));
                filiere.setDepartement(departement);
            }
            if (filiereRequestDTO.getRegleCalculId() != null) {
                RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(filiereRequestDTO.getRegleCalculId());
                if (regleCalcul != null) filiere.setRegleCalcul(regleCalcul);
                else throw new RegleCalculNotFoundException(filiereRequestDTO.getRegleCalculId());
            }
            filiereRepository.save(filiere);
            FiliereResponseDTO filiereResponseDTO = filiereMapper.fromEntityToResponseDTO(filiere);
            if (filiere.getDepartement() != null) {
                filiereResponseDTO.setDepartementId(filiere.getDepartement().getId());
                filiereResponseDTO.setDepartementIntitule(filiere.getDepartement().getIntitule());
            }
            if (filiere.getRegleCalcul() != null)
                filiereResponseDTO.setRegleCalculId(filiere.getRegleCalcul().getId());
            return filiereResponseDTO;
        }
    }


    @Override
    public Filiere getFiliere(Long id) throws FiliereNotFoundException {
        return filiereRepository.findById(id).orElseThrow(() -> new FiliereNotFoundException("Filiere with id " + id + " not found"));

    }

    @Override
    public FiliereResponseDTO getFiliere(String code) throws FiliereNotFoundException {
        Filiere filiere = filiereRepository.findByCode(code);
        if (filiere == null) throw new FiliereNotFoundException("Filiere with code " + code + " not found");
        FiliereResponseDTO filiereResponseDTO = filiereMapper.fromEntityToResponseDTO(filiere);
        Departement departement = filiere.getDepartement();
        if (departement != null) {
            filiereResponseDTO.setDepartementId(departement.getId());
            filiereResponseDTO.setDepartementIntitule(departement.getIntitule());
        }
        RegleCalcul regleCalcul = filiere.getRegleCalcul();
        if (regleCalcul != null) filiereResponseDTO.setRegleCalculId(regleCalcul.getId());
        return filiereResponseDTO;
    }


    @Override
    public List<FiliereResponseDTO> getFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        List<FiliereResponseDTO> filiereResponseDTOS = new ArrayList<>();
        for (Filiere filiere : filieres) {
            FiliereResponseDTO filiereResponseDTO = filiereMapper.fromEntityToResponseDTO(filiere);
            if (filiere.getDepartement() != null) {
                //Departement departement = departementRepository.findById(filiere.getDepartement().getId()).orElse(null);
                filiereResponseDTO.setDepartementId(filiere.getDepartement().getId());
                filiereResponseDTO.setDepartementIntitule(filiere.getDepartement().getIntitule());
            }
            if (filiere.getRegleCalcul() != null) {
                filiereResponseDTO.setRegleCalculId(filiere.getRegleCalcul().getId());
            }

            filiereResponseDTOS.add(filiereResponseDTO);
        }
        return filiereResponseDTOS;
    }

    @Override
    public void deleteFiliere(String code) throws FiliereNotFoundException {
        Filiere filiere = filiereRepository.findByCode(code);
        if (filiere == null) throw new FiliereNotFoundException("Filiere with code " + code + " not found");
        //filiereRepository.delete(filiere);

        filiere.setSoftDelete(true);
        filiereRepository.save(filiere);
    }

    @Override
    public List<FiliereResponseDTO> getFiliereWithDeptAndProf(Long idProf, Long idDept, String libelSemestre) throws ModuleNotFoundException {
        if(idProf!=null && idDept!=null){
            Departement departement= departementRepository.findByIdAndSoftDeleteIsFalse(idDept);
            Professeur professeur= professeurRepository.findByIdAndSoftDeleteIsFalse(idProf);
            Semestre semestre = semestreRepository.findByLibelle(libelSemestre);
            if(professeur!=null  && departement!=null && semestre!=null ){
                List<Element> elements = elementRepository.findByCleEtrangere(idProf);
                if(elements!=null){
                    List<Filiere> filieres = new ArrayList<>();
                    for (Element e: elements) {
                        Module module = moduleRepository.findById(e.getModule().getId()).orElseThrow( ()-> new ModuleNotFoundException(e.getModule().getId()));
                        if(module!=null && module.getSemestre().getId()==semestre.getId() ) {
                            Filiere filiere= filiereRepository.findById(module.getFiliere().getId()).orElse(null);
                            if(filiere!=null && filiere.getDepartement().getId()== idDept ){
                                filieres.add(filiere);
                            }
                        }
                    }
                    List<FiliereResponseDTO> responses = filieres.stream()
                            .map(f -> filiereMapper.fromEntityToResponseDTO(f))
                            .collect(Collectors.toList());

                    Set<FiliereResponseDTO> uniqueResponses = new HashSet<>(responses);
                    return new ArrayList<>(uniqueResponses);
                }
            }
        }
        return null;
    }


}
