package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.FiliereMapper;
import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.repositories.DepartementRepository;
import ma.enset.delibrations.repositories.FiliereRepository;
import ma.enset.delibrations.repositories.RegleCalculRepository;
import ma.enset.delibrations.services.FiliereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    private String generateCode() {
        //TODO: Generate Filiere code based on the format TI00006
        return "CODE"+System.currentTimeMillis();
    }
    
    
    @Override
    public FiliereResponseDTO createFiliere(FiliereRequestDTO filiereRequestDTO) throws RegleCalculNotFoundException {
        if (filiereRequestDTO.getCode() == null) filiereRequestDTO.setCode(generateCode());
        else {
            Filiere filiere = filiereRepository.findByCode(filiereRequestDTO.getCode());
            if (filiere != null) throw new RuntimeException("Filiere with code "+filiereRequestDTO.getCode()+" already exists");
        }
        filiereRequestDTO.setCode(generateCode());

        Filiere savedFiliere = filiereRepository.save(filiereMapper.fromRequestDTOtoEntity(filiereRequestDTO));

        if (filiereRequestDTO.getRegleCalculId()!=null){
            RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(filiereRequestDTO.getRegleCalculId());
            if(regleCalcul!=null) savedFiliere.setRegleCalcul(regleCalcul);
            else throw new RegleCalculNotFoundException(filiereRequestDTO.getRegleCalculId());
        }

        filiereRepository.save(savedFiliere);

        FiliereResponseDTO filiereResponseDTO = filiereMapper.fromEntityToResponseDTO(savedFiliere);
        if (savedFiliere.getRegleCalcul()!=null)
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
                Departement departement = departementRepository.findById(filiereRequestDTO.getDepartementId()).orElseThrow(()->new DepartementNotFoundException(filiereRequestDTO.getDepartementId()));
                filiere.setDepartement(departement);
            }
            if (filiereRequestDTO.getRegleCalculId() != null) {
                RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(filiereRequestDTO.getRegleCalculId());
                if(regleCalcul!=null)filiere.setRegleCalcul(regleCalcul);
                else throw new RegleCalculNotFoundException(filiereRequestDTO.getRegleCalculId());
            }
            filiereRepository.save(filiere);
            FiliereResponseDTO filiereResponseDTO=filiereMapper.fromEntityToResponseDTO(filiere);
            if (filiere.getDepartement() != null){
                filiereResponseDTO.setDepartementId(filiere.getDepartement().getId());
            }
            if(filiere.getRegleCalcul()!=null)
            filiereResponseDTO.setRegleCalculId(filiere.getRegleCalcul().getId());
            return filiereResponseDTO;
        }
    }

    @Override
    public Filiere getFiliere(Long id) throws FiliereNotFoundException {
        return filiereRepository.findById(id).orElseThrow(()->new FiliereNotFoundException("Filiere with id "+id+" not found"));

    }

    @Override
    public FiliereResponseDTO getFiliere(String code) throws FiliereNotFoundException {
        //Find by code and soft delete is false !!!
        //Filiere filiere = filiereRepository.findByCode(code);
        Filiere filiere = filiereRepository.findByCodeAndSoftDeleteIsFalse(code);
        if (filiere == null) throw new FiliereNotFoundException(code);
        FiliereResponseDTO filiereResponseDTO= filiereMapper.fromEntityToResponseDTO(filiere);
        Departement departement = filiere.getDepartement();
        if (departement!=null) filiereResponseDTO.setDepartementId(departement.getId());
        RegleCalcul regleCalcul = filiere.getRegleCalcul();
        if(regleCalcul!=null) filiereResponseDTO.setRegleCalculId(regleCalcul.getId());
        return filiereResponseDTO;
    }

    @Override
    public List<FiliereResponseDTO> getFilieres() {
        //find all and soft delete is false !!!
        //List<Filiere> filieres = filiereRepository.findAll();
        List<Filiere> filieres = filiereRepository.findBySoftDeleteIsFalse();
        List<FiliereResponseDTO> filiereResponseDTOS = new ArrayList<>();
        for (Filiere filiere : filieres) {
            FiliereResponseDTO filiereResponseDTO = filiereMapper.fromEntityToResponseDTO(filiere);
            if (filiere.getDepartement() != null){
                filiereResponseDTO.setDepartementId(filiere.getDepartement().getId());
            }
            if (filiere.getRegleCalcul() != null){
                filiereResponseDTO.setRegleCalculId(filiere.getRegleCalcul().getId());
            }
            filiereResponseDTOS.add(filiereResponseDTO);
        }
        return filiereResponseDTOS;
    }

    @Override
    public void deleteFiliere(String code) throws FiliereNotFoundException {
        Filiere filiere = filiereRepository.findByCode(code);
        if (filiere == null) throw new FiliereNotFoundException("Filiere with code "+code+" not found");
        //filiereRepository.delete(filiere);

        filiere.setSoftDelete(true);
        filiereRepository.save(filiere);
    }
    
}
