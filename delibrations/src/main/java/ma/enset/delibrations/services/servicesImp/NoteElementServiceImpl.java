package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.NoteElementMapper;
import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.NoteElement;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.repositories.ElementRepository;
import ma.enset.delibrations.repositories.NoteElementRepository;
import ma.enset.delibrations.services.NoteElementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class NoteElementServiceImpl implements NoteElementService {
    NoteElementRepository noteElementRepository;
    NoteElementMapper noteElementMapper;
    ElementRepository elementRepository;

    @Override
    public NoteElementResponseDTO createNoteElement(NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException {
        NoteElement noteElement = noteElementMapper.fromRequestDTOtoEntity(noteElementRequestDTO);
        //Check if the element exists
        Element element = elementRepository.findById(noteElementRequestDTO.getIdElement()).orElseThrow(()-> new ElementNotFoundException(noteElementRequestDTO.getIdElement()+" not found"));
        //TODO: Check InscriptionPédagogique
        noteElement.setElement(element);
        noteElement.setCreatedAt(new Date());
        NoteElement savedNoteElement = noteElementRepository.save(noteElement);
        return noteElementMapper.fromEntitytoResponseDTO(savedNoteElement);
    }

    @Override
    public NoteElementResponseDTO updateNoteElement(NoteElementRequestDTO noteElementRequestDTO) {
        return null;
    }

    @Override
    public NoteElementResponseDTO getNoteElement(Long id) {
        return null;
    }

    @Override
    public void deleteNoteElement(Long id) {

    }

    @Override
    public List<NoteElementResponseDTO> getNoteElements() {
        return null;
    }
}
