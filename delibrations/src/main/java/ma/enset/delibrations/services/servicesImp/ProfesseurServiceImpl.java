package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.ElementMapper;
import ma.enset.delibrations.dtos.mappers.ProfesseurMapper;
import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.Professeur;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ProfesseurNotFoundException;
import ma.enset.delibrations.repositories.ElementRepository;
import ma.enset.delibrations.repositories.ProfesseurRepository;
import ma.enset.delibrations.services.ProfesseurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProfesseurServiceImpl implements ProfesseurService {
    private ProfesseurRepository professeurRepository;
    private ProfesseurMapper professeurMapper;
    private ElementMapper elementMapper;
    private ElementRepository elementRepository;

    @Override
    public ProfesseurResponseDTO createProfesseur(ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException {
        if(professeurRequestDTO!=null){
            Professeur professeur = professeurMapper.fromRequestDTOtoEntity(professeurRequestDTO);
            professeur.setCreatedAt(new Date());
            professeurRepository.save(professeur);
            return professeurMapper.fromEntityToResponseDTO(professeur);
        }
       throw new CannotProceedException("Professeur is null");
    }

    @Override
    public ProfesseurResponseDTO updateProfesseur(Long id, ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException {
        if (id != null && professeurRequestDTO != null) {
            Professeur professeur = professeurRepository.findById(id).orElseThrow(() -> new ProfesseurNotFoundException(id));
            if (professeurRequestDTO.getCin() != null) professeur.setCin(professeurRequestDTO.getCin());
            if (professeurRequestDTO.getNom() != null) professeur.setNom(professeurRequestDTO.getNom());
            if (professeurRequestDTO.getPrenom() != null) professeur.setPrenom(professeurRequestDTO.getPrenom());
            if (professeurRequestDTO.getEmail() != null) professeur.setEmail(professeurRequestDTO.getEmail());
            if (professeurRequestDTO.getElementModules() != null){
                professeur.getElementModules().clear();
                professeurRequestDTO.getElementModules().forEach(elementModuleRequestDTO -> {
                    Element element = elementMapper.fromRequestDTOtoEntity(elementModuleRequestDTO);
                    element.setProfesseur(professeur);
                    elementRepository.save(element);
                    professeur.getElementModules().add(element);
                });
            }
            professeur.setUpdatedOn(new Date());
            professeurRepository.save(professeur);
            return professeurMapper.fromEntityToResponseDTO(professeur);
        }
        throw new ProfesseurNotFoundException(id);
    }

    @Override
    public ProfesseurResponseDTO getProfesseur(Long id) throws ProfesseurNotFoundException {
        if (id == null) return null;
        Professeur professeur = professeurRepository.findById(id).orElseThrow(() -> new ProfesseurNotFoundException(id));
        return professeurMapper.fromEntityToResponseDTO(professeur);
    }

    @Override
    public List<ProfesseurResponseDTO> getProfesseurs() {
        List<Professeur> professeurs = professeurRepository.findAll();
        List<ProfesseurResponseDTO> professeurResponseDTOS = new ArrayList<>();
        professeurs.forEach(professeur -> {
            professeurResponseDTOS.add(professeurMapper.fromEntityToResponseDTO(professeur));
        });
        return professeurResponseDTOS;
    }

    @Override
    public void deleteProfesseur(Long id) throws ProfesseurNotFoundException {
        if (id == null) return;
        Professeur professeur = professeurRepository.findById(id).orElseThrow(() -> new ProfesseurNotFoundException(id));
        professeurRepository.delete(professeur);
    }
}
