package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.ElementRequestDTO;
import ma.enset.delibrations.dtos.responses.ElementResponseDTO;
import ma.enset.delibrations.entities.Element;
import org.springframework.beans.BeanUtils;

public class ElementMapper {
    //ElementResponseDTO
    public ElementRequestDTO fromEntitytoRequestDTO(Element element){
        ElementRequestDTO elementRequestDTO = new ElementRequestDTO();
        BeanUtils.copyProperties(element, elementRequestDTO);
        return  elementRequestDTO;
    }

    public Element fromRequestDTOtoEntity(ElementRequestDTO elementRequestDTO) {
        Element element = new Element();
        BeanUtils.copyProperties(elementRequestDTO, element);
        return element;
    }
    //ResponseDTO
    public ElementResponseDTO fromEntitytoResponseDTO(Element element){
        ElementResponseDTO elementResponseDTO = new ElementResponseDTO();
        BeanUtils.copyProperties(element, elementResponseDTO);
        return  elementResponseDTO;
    }

    public Element fromResponseDTOtoEntity(ElementResponseDTO elementResponseDTO) {
        Element element = new Element();
        BeanUtils.copyProperties(elementResponseDTO, element);
        return element;
    }

}
