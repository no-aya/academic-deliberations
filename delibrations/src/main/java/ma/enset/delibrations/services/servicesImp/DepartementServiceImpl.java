package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.DepartementMapper;
import ma.enset.delibrations.dtos.mappers.FiliereMapper;
import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.entities.Departement;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.repositories.DepartementRepository;
import ma.enset.delibrations.services.DepartementService;
import ma.enset.delibrations.services.FiliereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DepartementServiceImpl implements DepartementService
{
    private DepartementMapper departementMapper;
    private DepartementRepository departementRepository;
    private FiliereMapper filiereMapper;
    private FiliereService filiereService;

    @Override
    public DepartementResponseDTO createDepartement(DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null){
            Departement departement = departementMapper.fromRequestDTOtoEntity(departementRequestDTO);
            departement.setCreatedAt(new Date());
            /*Filieres*/
//            for (Filiere filiere : departement.getFilieres()) {
//              filiere.setDepartement(departement);
            //filiereService.createFiliere(filiereMapper.fromEntityToRequestDTO(filiere));
         //   }
            departementRepository.save(departement);
            return departementMapper.fromEntityToResponseDTO(departement);
        }
        throw new CannotProceedException("Departement is null");
    }

    @Override
    public DepartementResponseDTO updateDepartement(String id, DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if (id != null && departementRequestDTO != null) {
            Departement departement = departementRepository.findByCodeAndSoftDeleteIsFalse(id);
            if (departement == null) throw new DepartementNotFoundException(id);
            if (departementRequestDTO.getCode() != null) departement.setCode(departementRequestDTO.getCode());
            if (departementRequestDTO.getIntitule() != null) departement.setIntitule(departementRequestDTO.getIntitule());
            if (departementRequestDTO.getFilieres() != null){
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
            }
            departement.setUpdatedAt(new Date());
            departementRepository.save(departement);
            return departementMapper.fromEntityToResponseDTO(departement);
        }
        throw new DepartementNotFoundException(id);
    }

    @Override
    public DepartementResponseDTO getDepartement(String  id) throws DepartementNotFoundException {
        if (id == null) return null;
        Departement departement = departementRepository.findByCodeAndSoftDeleteIsFalse(id);
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
}
