package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.Professeur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class ProfesseurMapper {

    //ProfesseurResponseDTO

    public ProfesseurResponseDTO fromEntityToResponseDTO(Professeur professeur){
        ProfesseurResponseDTO professeurResponseDTO = new ProfesseurResponseDTO();
        /*convert elemnets to ids*/
        BeanUtils.copyProperties(professeur, professeurResponseDTO);
        if (professeur.getElementModules() == null) return professeurResponseDTO;
        List<Long> elementIds = new ArrayList<>();
        for (Element element : professeur.getElementModules()) {
            elementIds.add(element.getId());
        }
        professeurResponseDTO.setElementModules(elementIds.toArray(new Long[0]));
        return  professeurResponseDTO;
    }

    public Professeur fromResponseDTOtoEntity(ProfesseurResponseDTO professeurResponseDTO){
        Professeur professeur = new Professeur();
        BeanUtils.copyProperties(professeurResponseDTO, professeur);
        Long[] elementIds = professeurResponseDTO.getElementModules();
        for (Long elementId : elementIds) {
            Element element = new Element();
            element.setId(elementId);
            professeur.getElementModules().add(element);
        }
        return  professeur;
    }

    //ProfesseurRequestDTO
    public ProfesseurRequestDTO fromEntitytoRequestDTO(Professeur professeur){
        ProfesseurRequestDTO professeurRequestDTO = new ProfesseurRequestDTO();
        professeurRequestDTO.setElementModules(
                professeur.getElementModules()
                        .stream()
                        .map(Element::getId
                        ).toArray(Long[]::new)
        );
        BeanUtils.copyProperties(professeur, professeurRequestDTO);
        return  professeurRequestDTO;
    }

    public Professeur fromRequestDTOtoEntity(ProfesseurRequestDTO professeurRequestDTO){
        Professeur professeur = new Professeur();
        //TODO: Ids are not copied
        BeanUtils.copyProperties(professeurRequestDTO, professeur);
        Long[] elementIds = professeurRequestDTO.getElementModules();
        System.out.println("elementIds = " + Arrays.toString(elementIds));
        List<Element> professeurElementModules = new ArrayList<>();
        if (elementIds != null) {
           for (Long elementId : elementIds) {
               Element element = new Element();
               element.setId(elementId);
               professeurElementModules.add(element);
           }
           professeur.setElementModules(professeurElementModules);
         }
        return  professeur;
    }






}
