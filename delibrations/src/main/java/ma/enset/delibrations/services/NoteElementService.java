package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.exceptions.NoteElementNotFoundException;

import java.util.List;

public interface NoteElementService {
    NoteElementResponseDTO createNoteElement(NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException;
    NoteElementResponseDTO updateNoteElement(NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException, NoteElementNotFoundException;
    NoteElementResponseDTO getNoteElement(Long id) throws NoteElementNotFoundException;
    void deleteNoteElement(Long id) throws NoteElementNotFoundException;
    List<NoteElementResponseDTO> getNoteElements();
}