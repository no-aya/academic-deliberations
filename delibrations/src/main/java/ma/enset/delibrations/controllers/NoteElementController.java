package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.exceptions.NoteElementNotFoundException;
import ma.enset.delibrations.services.NoteElementService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/note-element")
public class NoteElementController {
    NoteElementService noteElementService;
    @PostMapping("/add")
    public NoteElementResponseDTO createNoteElement(@RequestBody NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException {
        return noteElementService.createNoteElement(noteElementRequestDTO);
    }

    @PutMapping("/{id}")
    public NoteElementResponseDTO updateNoteElement(@RequestBody NoteElementRequestDTO noteElementRequestDTO, @PathVariable Long id) throws ElementNotFoundException, NoteElementNotFoundException {
        noteElementRequestDTO.setId(id);
        return noteElementService.updateNoteElement(noteElementRequestDTO);
    }

}
