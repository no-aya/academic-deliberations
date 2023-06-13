package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.NoteModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteModuleResponseDTO;
import ma.enset.delibrations.entities.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteModuleNotFoundException;
import ma.enset.delibrations.services.NoteModuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/note-module")
public class NoteModuleRestController {

    private NoteModuleService noteModuleService;

    @GetMapping("/all")
    public List<NoteModuleResponseDTO> getAllNotesModules(){
        return noteModuleService.getNotesModules();

    }
    @GetMapping("/{id}")
    public  NoteModuleResponseDTO getNoteModule(@PathVariable Long id) throws NoteModuleNotFoundException {
            return  noteModuleService.getNoteModule(id);
    }


    @PostMapping("/add")
    public NoteModuleResponseDTO createNoteModule(@RequestBody NoteModuleRequestDTO noteModuleRequestDTO) throws NoteModuleNotFoundException, ModuleNotFoundException {
            return  noteModuleService.createNoteModule(noteModuleRequestDTO);
    }


    @PutMapping("/{id}")
    public NoteModuleResponseDTO updateNoteModule(@PathVariable  Long id,@RequestBody NoteModuleRequestDTO noteModuleRequestDTO) throws NoteModuleNotFoundException, ModuleNotFoundException {
            noteModuleRequestDTO.setId(id);
            return noteModuleService.updateNoteModule(noteModuleRequestDTO);
    }


    @DeleteMapping("/{id}")
    public Boolean deleteNoteModule(@PathVariable Long id)throws NoteModuleNotFoundException {
            noteModuleService.deleteNoteModule(id);
            return true;
    }
}
