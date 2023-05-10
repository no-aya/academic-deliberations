package ma.enset.delibrations.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.requests.NoteSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteSemestreResponseDTO;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.services.NoteSemestreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/note-semestre")
public class NoteSemestreController {

    private NoteSemestreService noteSemestreService;

    @GetMapping("/all")
    public List<NoteSemestreResponseDTO> getAllNoteSemestres(){
        return noteSemestreService.getAllNotesSemestre();
    }

    @GetMapping("/{id}")
    public NoteSemestreResponseDTO getNoteSemestre(@PathVariable Long id) throws NoteSemestreNotFoundException {
        if(id!=null) return noteSemestreService.getNoteSemestre(id);
        return null;
    }

    @PostMapping("/add")
    public NoteSemestreResponseDTO createNoteSemestre(@RequestBody NoteSemestreRequestDTO noteSemestreRequestDTO) throws SemestreNotFoundException {
        return noteSemestreService.addNoteSemestre(noteSemestreRequestDTO);
    }

    @PutMapping("/{id}")
    public NoteSemestreResponseDTO updateNoteSemestre(@PathVariable Long id, @RequestBody NoteSemestreRequestDTO noteSemestreRequestDTO) throws NoteSemestreNotFoundException, ProfesseurNotFoundException, CannotProceedException, ElementNotFoundException, SemestreNotFoundException {
        if(id!=null) {
            noteSemestreRequestDTO.setId(id);
            return noteSemestreService.updateNoteSemestre(noteSemestreRequestDTO);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteNoteSemestre(@PathVariable Long id) throws NoteSemestreNotFoundException {
        if(id!=null) {
            noteSemestreService.deleteNoteSemestre(id);
            return true;
        }
        return false;
    }

}

