package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ProfesseurNotFoundException;
import ma.enset.delibrations.services.ProfesseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/professeur")
public class ProfesseurController {
    private ProfesseurService professeurService;

    @GetMapping("/professeurs")
    public List<ProfesseurResponseDTO>  getAllProfesseurs(@RequestBody ProfesseurRequestDTO professeurRequestDTO){
        if(professeurRequestDTO!=null) return professeurService.getProfesseurs();
        return null;
    }

    @GetMapping("/{id}")
    public ProfesseurResponseDTO getProfesseur(@PathVariable Long id){
        if(id!=null) return professeurService.getProfesseur(id);
        return null;
    }

    @PostMapping
    public ProfesseurResponseDTO createProfesseur(@RequestBody ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException {
        if(professeurRequestDTO!=null) return professeurService.createProfesseur(professeurRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public ProfesseurResponseDTO updateProfesseur(@PathVariable Long id, @RequestBody ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException {
        if(professeurRequestDTO!=null && id!=null) return professeurService.updateProfesseur(id,professeurRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProfesseur(@PathVariable Long id) throws ProfesseurNotFoundException {
        if(id!=null)  professeurService.deleteProfesseur(id);
    }

}
