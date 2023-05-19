package ma.enset.delibrations.controllers;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.services.SemestreService;
import org.springframework.web.bind.annotation.*;

import javax.naming.CannotProceedException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/semestre")
public class SemestreController {
    private SemestreService semestreService;

    @GetMapping("/all")
    public List<SemestreResponseDTO> getAllSemestres(){
         return semestreService.getSemestres();

    }
    @GetMapping("/{id}")
    public SemestreResponseDTO getSemestre(@PathVariable String id) throws SemestreNotFoundException {
        if(id!=null) return semestreService.getSemestre(id);
        return null;
    }
    @PostMapping("/add")
    public SemestreResponseDTO createSemestre(@RequestBody SemestreRequestDTO semestreRequestDTO) throws CannotProceedException, NoteSemestreNotFoundException {
        if(semestreRequestDTO!=null) return semestreService.createSemestre(semestreRequestDTO);
        return null;
    }
    @PutMapping("/{id}")
    public SemestreResponseDTO updateSemestre(@PathVariable String id, @RequestBody SemestreRequestDTO semestreRequestDTO) throws SemestreNotFoundException, NoteSemestreNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException, AnneeUnivNotFoundException {
        if(semestreRequestDTO!=null && id!=null)
            return semestreService.updateSemestre(id,semestreRequestDTO);
        return null;
    }
    @DeleteMapping("/{id}")
    public Boolean deleteSemestre(@PathVariable String id) throws SemestreNotFoundException {
        if( id!=null)  {
            semestreService.deleteSemestre(id);
            return true;
        }
        else return false;
    }


}
