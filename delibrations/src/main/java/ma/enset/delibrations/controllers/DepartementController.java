package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.entities.exceptions.*;
import ma.enset.delibrations.services.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departement")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartementController {
    private DepartementService departementService;

    @GetMapping("/all")
    public List<DepartementResponseDTO> getAllDepartements(){
        return departementService.getDepartements();
    }

    @GetMapping("/{code}")
    public DepartementResponseDTO getDepartement(@PathVariable Long id) throws DepartementNotFoundException {
        if(id!=null) return departementService.getDepartement(id);
        return null;
    }

    @PostMapping("/add")
    public DepartementResponseDTO createDepartement(@RequestBody DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null) return departementService.createDepartement(departementRequestDTO);
        return null;
    }

    @PutMapping("/{code}")
    public DepartementResponseDTO updateDepartement(@PathVariable Long id, @RequestBody DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null && id!=null)
            return departementService.updateDepartement(id, departementRequestDTO);
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) {
            departementService.deleteDepartement(code);
            return true;

        }else return false;
    }

    @GetMapping
    public List<DepartementResponseDTO> getDepartementsByProf(@RequestParam Long idProf,@RequestParam String libelS) throws ProfesseurNotFoundException, ModuleNotFoundException, FiliereNotFoundException, DepartementNotFoundException {
        if(idProf
                !=null && libelS!=null) {
            return  departementService.getDepartementsByProf(idProf, libelS);
        }else return null;
    }
}
