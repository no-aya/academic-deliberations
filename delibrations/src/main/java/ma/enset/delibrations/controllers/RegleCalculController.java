package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.RegleCalculRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleCalculResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.services.RegleCalculService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regleCalcul")
public class RegleCalculController {
    private RegleCalculService regleCalculService;

    @GetMapping("/all")
    public List<RegleCalculResponseDTO>  getAllReglesCalcul(){
        return regleCalculService.getReglesCalcul();
    }

    @GetMapping("/{id}")
    public RegleCalculResponseDTO getRegleCalcul(@PathVariable Long id) throws RegleCalculNotFoundException {
        if(id!=null) return regleCalculService.getRegleCalcul(id);
        return null;
    }

    @PostMapping("/add")
    public RegleCalculResponseDTO createRegleCalcul(@RequestBody RegleCalculRequestDTO regleCalculRequestDTO){
        if(regleCalculRequestDTO!=null) return regleCalculService.createRegleCalcul(regleCalculRequestDTO);
        return null;
    }

    //Afin d'ajouter une filiere, regle de calcule || regle de semestre
    @PutMapping("/ajout/{id}")
    public RegleCalculResponseDTO updateRegleCalculParAjout(@PathVariable Long id, @RequestBody RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException {
        if(regleCalculRequestDTO!=null && id!=null) return regleCalculService.updateRegleCalculParAjout(id,regleCalculRequestDTO);
        return null;
    }

    //Afin de supprimer une filiere, regle de calcule || regle de semestre
    @PutMapping("/supression/{id}")
    public RegleCalculResponseDTO updateRegleCalculParSupression(@PathVariable Long id, @RequestBody RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException {
        if(regleCalculRequestDTO!=null && id!=null) return regleCalculService.updateRegleCalculParSuppression(id,regleCalculRequestDTO);
        return null;
    }

  /*  @PutMapping("/{id}")
    public RegleCalculResponseDTO updateRegleCalcul(@PathVariable Long id, @RequestBody RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException {
        if(regleCalculRequestDTO!=null && id!=null) return regleCalculService.updateRegleCalcul(id,regleCalculRequestDTO);
        return null;
    }*/

    @DeleteMapping("/{id}")
    public void deleteRegleCalcul(@PathVariable Long id) throws RegleCalculNotFoundException {
        if( id!=null)  regleCalculService.deleteRegleCalcul(id);
    }

}
