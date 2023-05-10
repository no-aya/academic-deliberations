package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.NoteElement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NoteElementMapper {

    //NoteElementResponseDTO
    //Mapping the Element to elementId and vice versa
    public NoteElementResponseDTO fromEntitytoResponseDTO(NoteElement noteElement){
        NoteElementResponseDTO noteElementResponseDTO = new NoteElementResponseDTO();
        BeanUtils.copyProperties(noteElement, noteElementResponseDTO);
        //TODO: Inscription pédagogique
        noteElementResponseDTO.setIdElement(noteElement.getElement().getId());
        return  noteElementResponseDTO;
    }

    public NoteElement fromResponseDTOtoEntity(NoteElementResponseDTO noteElementResponseDTO) {
        NoteElement noteElement = new NoteElement();
        BeanUtils.copyProperties(noteElementResponseDTO, noteElement);
        Element element = new Element();
        element.setId(noteElementResponseDTO.getIdElement());
        //TODO: Inscription pédagogique
        noteElement.setElement(element);
        return noteElement;
    }

    //NoteElementRequestDTO
    //Mapping the idElement to Element and vice versa
    public NoteElementRequestDTO fromEntitytoRequestDTO(NoteElement noteElement){
        NoteElementRequestDTO noteElementRequestDTO = new NoteElementRequestDTO();
        BeanUtils.copyProperties(noteElement, noteElementRequestDTO);
        noteElementRequestDTO.setIdElement(noteElement.getElement().getId());
        return  noteElementRequestDTO;
    }

    public NoteElement fromRequestDTOtoEntity(NoteElementRequestDTO noteElementRequestDTO) {
        NoteElement noteElement = new NoteElement();
        BeanUtils.copyProperties(noteElementRequestDTO, noteElement);
        Element element = new Element();
        element.setId(noteElementRequestDTO.getIdElement());
        noteElement.setElement(element);
        //TODO: Inscription pédagogique
        return noteElement;
    }
}
