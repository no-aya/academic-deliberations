package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.ElementNotFoundException;
import ma.enset.delibrations.entities.exceptions.ProfesseurNotFoundException;
import ma.enset.delibrations.services.ProfesseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/professeur")
@CrossOrigin("*")
public class ProfesseurController {
    private ProfesseurService professeurService;

    @GetMapping("/all")
    public List<ProfesseurResponseDTO>  getAllProfesseurs(){
        return professeurService.getProfesseurs();
    }

    @GetMapping("/{id}")
    public ProfesseurResponseDTO getProfesseur(@PathVariable String id) throws ProfesseurNotFoundException {
        if(id!=null) return professeurService.getProfesseur(id);
        return null;
    }

    @PostMapping("/add")
    public ProfesseurResponseDTO createProfesseur(@RequestBody ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException, ProfesseurNotFoundException, ElementNotFoundException {
        if(professeurRequestDTO!=null) return professeurService.createProfesseur(professeurRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public ProfesseurResponseDTO updateProfesseur(@PathVariable String id, @RequestBody ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException, ElementNotFoundException {
        if(professeurRequestDTO!=null && id!=null)
            return professeurService.updateProfesseur(id, professeurRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProfesseur(@PathVariable String id) throws ProfesseurNotFoundException {
        if(id!=null) {
            professeurService.deleteProfesseur(id);
            return true;

        }else return false;
    }

}
