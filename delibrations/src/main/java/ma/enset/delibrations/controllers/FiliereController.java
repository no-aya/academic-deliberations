package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.exceptions.*;
import ma.enset.delibrations.services.FiliereService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filiere")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public FiliereResponseDTO createFiliere(@RequestBody FiliereRequestDTO filiereRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, CannotProceedException, RegleCalculNotFoundException {
        return filiereService.createFiliere(filiereRequestDTO);
    }

    @PutMapping("/{code}")
    public FiliereResponseDTO updateFiliere(@PathVariable String code, @RequestBody FiliereRequestDTO filiereRequestDTO) throws FiliereNotFoundException, DepartementNotFoundException, RegleCalculNotFoundException {
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

    @GetMapping
    public List<FiliereResponseDTO> getFiliere(@RequestParam Long idProf, @RequestParam Long idDept,@RequestParam String libelS) throws FiliereNotFoundException, ModuleNotFoundException {
        if (idProf!=null && idDept!=null  && libelS!=null ) return filiereService.getFiliereWithDeptAndProf(idProf, idDept,libelS);
        return null;
    }

}
