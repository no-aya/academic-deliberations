package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.entities.exceptions.ElementNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteElementNotFoundException;
import ma.enset.delibrations.services.NoteElementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/note-element")
public class NoteElementController {
    NoteElementService noteElementService;

    @GetMapping("/all")
    public List<NoteElementResponseDTO> getNoteElements(){
        return noteElementService.getNoteElements();
    }

    @GetMapping("/{id}")
    public NoteElementResponseDTO getNoteElement(@PathVariable Long id) throws NoteElementNotFoundException {
        return noteElementService.getNoteElement(id);
    }

    @PutMapping("/{id}")
    public NoteElementResponseDTO updateNoteElement(@RequestBody NoteElementRequestDTO noteElementRequestDTO, @PathVariable Long id) throws ElementNotFoundException, NoteElementNotFoundException {
        noteElementRequestDTO.setId(id);
        return noteElementService.updateNoteElement(noteElementRequestDTO);
    }

    @PostMapping("/add")
    public NoteElementResponseDTO createNoteElement(@RequestBody NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException {
        return noteElementService.createNoteElement(noteElementRequestDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteNoteElement(@PathVariable Long id) throws NoteElementNotFoundException {
        noteElementService.deleteNoteElement(id);
        return true;
    }
}


