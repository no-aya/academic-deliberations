package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;

import java.util.List;

public interface NoteElementService {
    NoteElementResponseDTO createNoteElement(NoteElementResponseDTO noteElementResponseDTO);
    NoteElementResponseDTO updateNoteElement(Long id, NoteElementResponseDTO noteElementResponseDTO);
    NoteElementResponseDTO getNoteElement(Long id);
    void deleteNoteElement(Long id);
    List<NoteElementResponseDTO> getNoteElements();
}
