package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.services.NoteElementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/note-element")
public class NoteElementController {
    NoteElementService noteElementService;
    @PostMapping("/add")
    public NoteElementResponseDTO createNoteElement(@RequestBody NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException {
        return noteElementService.createNoteElement(noteElementRequestDTO);
    }

}
