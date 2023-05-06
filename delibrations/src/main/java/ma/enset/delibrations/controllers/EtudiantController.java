package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.services.EtudiantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/etudiant")
public class EtudiantController {
    private EtudiantService etudiantService;

    @GetMapping("/etudiants")
    public List<EtudiantResponseDTO>  getAllEtudiants(@RequestBody EtudiantRequestDTO etudiantRequestDTO){
        if(etudiantRequestDTO!=null) return etudiantService.getEtudiants();
        return null;
    }

    @GetMapping("/{id}")
    public EtudiantResponseDTO getEtudiant(@PathVariable String id){
        if(id!=null) return etudiantService.getEtudiant(id);
        return null;
    }

    @PostMapping
    public EtudiantResponseDTO createEtudiant(@RequestBody EtudiantRequestDTO etudiantRequestDTO){
        if(etudiantRequestDTO!=null) return etudiantService.createEtudiant(etudiantRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public EtudiantResponseDTO updateEtudiant(@PathVariable String id, @RequestBody EtudiantRequestDTO etudiantRequestDTO) throws EtudiantNotFoundException {
        if(etudiantRequestDTO!=null && id!=null) return etudiantService.updateEtudiant(id,etudiantRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable String id) throws EtudiantNotFoundException {
        if( id!=null)  etudiantService.deleteEtudiant(id);
    }

}
