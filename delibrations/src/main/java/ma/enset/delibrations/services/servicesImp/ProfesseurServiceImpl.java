package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.ElementMapper;
import ma.enset.delibrations.dtos.mappers.ProfesseurMapper;
import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.Professeur;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.ElementNotFoundException;
import ma.enset.delibrations.entities.exceptions.ProfesseurNotFoundException;
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
    private ElementServiceImpl elementService;
    private ElementRepository elementRepository;

    @Override
    public ProfesseurResponseDTO createProfesseur(ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException, ProfesseurNotFoundException, ElementNotFoundException {
        if(professeurRequestDTO!=null){
            Professeur professeur = professeurMapper.fromRequestDTOtoEntity(professeurRequestDTO);
            professeur.setCreatedAt(new Date());
            professeur.setSoftDelete(false);
            /*Element*/
           if (professeurRequestDTO.getElementModules() != null) {
               for (Long elementId : professeurRequestDTO.getElementModules()) {
                   Element element = elementService.getElement(elementId);
                   professeur.getElementModules().add(element);
                   element.setProfesseur(professeur);
                   elementRepository.save(element);
               }
            }
            professeurRepository.save(professeur);
            return professeurMapper.fromEntityToResponseDTO(professeur);
        }
       throw new CannotProceedException("Professeur is null");
    }

    @Override
    public ProfesseurResponseDTO updateProfesseur(String id, ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException, ElementNotFoundException {
        if (id != null && professeurRequestDTO != null) {
            Professeur professeur = professeurRepository.findByCinAndSoftDeleteIsFalse(id);
            if (professeur == null) throw new ProfesseurNotFoundException(id);
            if (professeurRequestDTO.getCin() != null) professeur.setCin(professeurRequestDTO.getCin());
            if (professeurRequestDTO.getNom() != null) professeur.setNom(professeurRequestDTO.getNom());
            if (professeurRequestDTO.getPrenom() != null) professeur.setPrenom(professeurRequestDTO.getPrenom());
            if (professeurRequestDTO.getEmail() != null) professeur.setEmail(professeurRequestDTO.getEmail());
            if (professeurRequestDTO.getElementModules() != null){
                Long[] elementModules = professeurRequestDTO.getElementModules();
                List<Element> elements = new ArrayList<>();
                for (Long elementId : elementModules) {
                    Element element = elementService.getElement(elementId);
                    if (element == null) throw new ElementNotFoundException("Element with id " + elementId + " not found");
                    elements.add(element);
                }
                professeur.setElementModules(elements);
                for (Element element : elements) {
                    element.setProfesseur(professeur);
                    elementService.updateElement(elementMapper.fromEntitytoRequestDTO(element));
                }
            }
            professeur.setUpdatedOn(new Date());
            professeurRepository.save(professeur);
            return professeurMapper.fromEntityToResponseDTO(professeur);
        }
        throw new ProfesseurNotFoundException(id);
    }

    @Override
    public ProfesseurResponseDTO getProfesseur(String id) throws ProfesseurNotFoundException {
        if (id == null) return null;
        Professeur professeur = professeurRepository.findByCinAndSoftDeleteIsFalse(id);
        if (professeur == null) throw new ProfesseurNotFoundException(id);
        return professeurMapper.fromEntityToResponseDTO(professeur);
    }

    @Override
    public List<ProfesseurResponseDTO> getProfesseurs() {
        List<Professeur> professeurs = professeurRepository.findBySoftDeleteIsFalse();
        List<ProfesseurResponseDTO> professeurResponseDTOS = new ArrayList<>();
        professeurs.forEach(professeur -> {
            professeurResponseDTOS.add(professeurMapper.fromEntityToResponseDTO(professeur));
        });
        return professeurResponseDTOS;
    }

    @Override
    public void deleteProfesseur(String id) throws ProfesseurNotFoundException {
        if (id == null) return;
        Professeur professeur = professeurRepository.findByCinAndSoftDeleteIsFalse(id);
        if (professeur == null) throw new ProfesseurNotFoundException(id);
        /*Hardcode del
        professeurRepository.delete(professeur);
         */
        professeur.setSoftDelete(true);
        professeurRepository.save(professeur);
    }
}
