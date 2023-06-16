package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.DepartementMapper;
import ma.enset.delibrations.dtos.mappers.FiliereMapper;
import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.exceptions.*;

import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.services.DepartementService;
import ma.enset.delibrations.services.FiliereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DepartementServiceImpl implements DepartementService {
    private DepartementMapper departementMapper;
    private DepartementRepository departementRepository;
    private FiliereMapper filiereMapper;
    private FiliereService filiereService;

    private ProfesseurRepository professeurRepository;
    private ElementRepository elementRepository;
    private ModuleRepository moduleRepository;
    private FiliereRepository filiereRepository;
    private SemestreRepository semestreRepository;

    @Override
    public DepartementResponseDTO createDepartement(DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null){
            Departement departement = departementMapper.fromRequestDTOtoEntity(departementRequestDTO);
            departement.setCreatedAt(new Date());

            departementRepository.save(departement);
            return departementMapper.fromEntityToResponseDTO(departement);
        }
        throw new CannotProceedException("Departement is null");
    }

    @Override
    public DepartementResponseDTO updateDepartement(Long id, DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if (id != null && departementRequestDTO != null) {
            Departement departement = departementRepository.findByIdAndSoftDeleteIsFalse(id);
            if (departement == null) throw new DepartementNotFoundException(id);
            if (departementRequestDTO.getCode() != null) departement.setCode(departementRequestDTO.getCode());
            if (departementRequestDTO.getIntitule() != null) departement.setIntitule(departementRequestDTO.getIntitule());
            /*if (departementRequestDTO.getFilieres() != null){
                Long[] filiereIDs = departementRequestDTO.getFilieres();
                List<Filiere> filieres = new ArrayList<>();
                for (Long filiereId : filiereIDs) {
                   Filiere filiere = filiereService.getFiliere(filiereId);
                    if (filiere == null) throw new FiliereNotFoundException("Filiere with id " + filiereId + " not found");
                    filieres.add(filiere);
                }
                departement.setFilieres(filieres);
                for (Filiere filiere : filieres) {
                    filiere.setDepartement(departement);
                    filiereService.updateFiliere(filiereMapper.fromEntityToRequestDTO(filiere));
                }
            }*/
            departement.setUpdatedAt(new Date());
            departementRepository.save(departement);
            return departementMapper.fromEntityToResponseDTO(departement);
        }
        throw new DepartementNotFoundException(id);
    }

    @Override
    public DepartementResponseDTO getDepartement(Long  id) throws DepartementNotFoundException {
        if (id == null) return null;
        Departement departement = departementRepository.findByIdAndSoftDeleteIsFalse(id);
        if (departement == null) throw new DepartementNotFoundException(id);
        return departementMapper.fromEntityToResponseDTO(departement);
      
    }

    @Override
    public List<DepartementResponseDTO> getDepartements() {
        List<Departement> departements = departementRepository.findBySoftDeleteIsFalse();
        List<DepartementResponseDTO> departementResponseDTOS = new ArrayList<>();
        departements.forEach(departement -> {
            departementResponseDTOS.add(departementMapper.fromEntityToResponseDTO(departement));
        });
        return departementResponseDTOS;
    }

    @Override
    public void deleteDepartement(String  id) throws DepartementNotFoundException {
        if (id == null) return;
        Departement departement = departementRepository.findByCodeAndSoftDeleteIsFalse(id);
        if (departement == null) throw new DepartementNotFoundException(id);
        departement.setSoftDelete(true);
        departementRepository.save(departement);
    }


    @Override
    public List<DepartementResponseDTO> getDepartementsByProf(Long idProf, String libelS) throws ProfesseurNotFoundException, ModuleNotFoundException, FiliereNotFoundException, DepartementNotFoundException {
        if(idProf==null || libelS==null) return null;
        Professeur professeur = professeurRepository.findByIdAndSoftDeleteIsFalse(idProf);
        Semestre semestre = semestreRepository.findByLibelle(libelS);
        if(professeur!=null && semestre!=null ){
            List<Element> elements = elementRepository.findByCleEtrangere(idProf);
            if(elements!=null){
                List<Departement> departements = new ArrayList<>();
                for (Element e: elements) {
                    Module module = moduleRepository.findById(e.getModule().getId()).orElseThrow( ()-> new ModuleNotFoundException(e.getModule().getId()));

                    if(module!=null && module.getSemestre().getId()==semestre.getId() ) {
                        Filiere filiere= filiereRepository.findById(module.getFiliere().getId()).orElse(null);
                        if(filiere!=null){
                            Departement departement= departementRepository.findById(filiere.getDepartement().getId()).orElseThrow(()-> new DepartementNotFoundException(filiere.getDepartement().getId()));
                            if(departement!=null) departements.add(departement);
                        }
                    }
                }
                List<DepartementResponseDTO> responses = departements.stream()
                        .map(departement -> departementMapper.fromEntityToResponseDTO(departement))
                        .collect(Collectors.toList());


                Map<DepartementResponseDTO, Integer> responseOccurrences = new HashMap<>();

                // Compter les occurrences de chaque élément
                for (DepartementResponseDTO response : responses) {
                    responseOccurrences.put(response, responseOccurrences.getOrDefault(response, 0) + 1);
                }

                // Créer une nouvelle liste en éliminant les doublons
                List<DepartementResponseDTO> uniqueResponses = new ArrayList<>(responseOccurrences.keySet());

                // Mettre à jour les occurrences pour chaque élément dans la nouvelle liste
                for (DepartementResponseDTO response : uniqueResponses) {
                    int occurrence = responseOccurrences.get(response);
                    response.setNbrElement(occurrence);
                }

                return uniqueResponses;

                // Set<DepartementResponseDTO> uniqueResponses = new HashSet<>(responses);
                //return new ArrayList<>(uniqueResponses);
            }
        }

        return null;

    }
}
