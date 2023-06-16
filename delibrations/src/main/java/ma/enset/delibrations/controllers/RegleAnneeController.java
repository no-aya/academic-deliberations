package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.RegleAnneeRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleAnneeResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleAnneeNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.services.RegleAnneeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regleAnnee")
public class RegleAnneeController {
    private RegleAnneeService regleAnneeService;

    @GetMapping("/all")
    public List<RegleAnneeResponseDTO>  getAllReglesCalcul(){
        return regleAnneeService.getReglesAnnee();
    }

    @GetMapping("/{id}")
    public RegleAnneeResponseDTO getRegleAnnee(@PathVariable Long id) throws RegleAnneeNotFoundException {
        if(id!=null) return regleAnneeService.getRegleAnnee(id);
        return null;
    }

    @PostMapping("/add")
    public RegleAnneeResponseDTO createRegleAnnee(@RequestBody RegleAnneeRequestDTO regleAnneeRequestDTO) throws RegleCalculNotFoundException {
        if(regleAnneeRequestDTO!=null) return regleAnneeService.createRegleAnnee(regleAnneeRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public RegleAnneeResponseDTO updateRegleAnnee(@PathVariable Long id, @RequestBody RegleAnneeRequestDTO regleAnneeRequestDTO) throws RegleAnneeNotFoundException, CannotProceedException {
        if(regleAnneeRequestDTO!=null && id!=null) return regleAnneeService.updateRegleAnnee(id,regleAnneeRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRegleAnnee(@PathVariable Long id) throws RegleAnneeNotFoundException {
        if( id!=null)  regleAnneeService.deleteRegleAnnee(id);
    }
}
