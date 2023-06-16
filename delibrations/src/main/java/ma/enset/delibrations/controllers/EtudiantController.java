package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.services.EtudiantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EtudiantController {
    private EtudiantService etudiantService;

    @GetMapping("/all")
    public List<EtudiantResponseDTO>  getAllEtudiants(){
        return etudiantService.getEtudiants();
    }

    @GetMapping("/{code}")
    public EtudiantResponseDTO getEtudiant(@PathVariable String code) throws EtudiantNotFoundException {
        if(code!=null) return etudiantService.getEtudiant(code);
        return null;
    }

    @PostMapping("/add")
    public EtudiantResponseDTO createEtudiant(@RequestBody EtudiantRequestDTO etudiantRequestDTO) throws CannotProceedException {
        if(etudiantRequestDTO!=null) return etudiantService.createEtudiant(etudiantRequestDTO);
        return null;
    }

    @PutMapping("/{code}")
    public EtudiantResponseDTO updateEtudiant(@PathVariable String code, @RequestBody EtudiantRequestDTO etudiantRequestDTO) throws EtudiantNotFoundException, CannotProceedException {
        if(etudiantRequestDTO!=null && code!=null) return etudiantService.updateEtudiant(code,etudiantRequestDTO);
        return null;
    }

    @DeleteMapping("/{code}")
    public void deleteEtudiant(@PathVariable String code) throws EtudiantNotFoundException {
        if( code!=null)
            etudiantService.deleteEtudiant(code);

    }

}
