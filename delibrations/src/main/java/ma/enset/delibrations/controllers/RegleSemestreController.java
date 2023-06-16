package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.RegleSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleSemestreResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleAnneeNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleSemestreNotFoundException;
import ma.enset.delibrations.services.RegleSemestreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regleSemestre")
public class RegleSemestreController {
    private RegleSemestreService regleSemestreService;

    @GetMapping("/all")
    public List<RegleSemestreResponseDTO>  getAllReglesSemestre(){
        return regleSemestreService.getReglesSemestre();
    }

    @GetMapping("/{id}")
    public RegleSemestreResponseDTO getRegleSemestre(@PathVariable Long id) throws RegleSemestreNotFoundException, RegleSemestreNotFoundException {
        if(id!=null) return regleSemestreService.getRegleSemestre(id);
        return null;
    }

    @PostMapping("/add")
    public RegleSemestreResponseDTO createRegleSemestre(@RequestBody RegleSemestreRequestDTO regleSemestreRequestDTO) throws RegleCalculNotFoundException {
        if(regleSemestreRequestDTO!=null) return regleSemestreService.createRegleSemestre(regleSemestreRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public RegleSemestreResponseDTO updateRegleSemestre(@PathVariable Long id, @RequestBody RegleSemestreRequestDTO regleSemestreRequestDTO) throws RegleAnneeNotFoundException, CannotProceedException, RegleSemestreNotFoundException {
        if(regleSemestreRequestDTO!=null && id!=null) return regleSemestreService.updateRegleSemestre(id,regleSemestreRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRegleAnnee(@PathVariable Long id) throws RegleAnneeNotFoundException {
        if( id!=null)  regleSemestreService.deleteRegleSemestre(id);
    }
}
