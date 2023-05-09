package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.exceptions.ElementNotFoundException;

import java.util.List;

public interface NoteElementService {
    NoteElementResponseDTO createNoteElement(NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException;
    NoteElementResponseDTO updateNoteElement(NoteElementRequestDTO noteElementRequestDTO);
    NoteElementResponseDTO getNoteElement(Long id);
    void deleteNoteElement(Long id);
    List<NoteElementResponseDTO> getNoteElements();
}
