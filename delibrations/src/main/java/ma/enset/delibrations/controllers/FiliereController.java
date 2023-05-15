package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.services.FiliereService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filiere")
public class FiliereController {
    private FiliereService filiereService;

    @GetMapping("/all")
    public List<FiliereResponseDTO> getAllFilieres(){
        return filiereService.getFilieres();
    }


    @GetMapping("/{code}")
    public FiliereResponseDTO getFiliere(@PathVariable String code) throws FiliereNotFoundException {
        if (code!=null) return filiereService.getFiliere(code);
        return null;
    }

    @PostMapping("/add")
    public FiliereResponseDTO createFiliere(@RequestBody FiliereRequestDTO filiereRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, CannotProceedException {
        return filiereService.createFiliere(filiereRequestDTO);
    }

    @PutMapping("/{code}")
    public FiliereResponseDTO updateFiliere(@PathVariable String code, @RequestBody FiliereRequestDTO filiereRequestDTO) throws FiliereNotFoundException, DepartementNotFoundException {
        if (code!=null) {
            filiereRequestDTO.setCode(code);
            return filiereService.updateFiliere(filiereRequestDTO);
        }
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteFiliere(@PathVariable String code) throws FiliereNotFoundException {
        if(code!=null) {
            filiereService.deleteFiliere(code);
            return true;
        }
        return false;
    }
}
