package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.services.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departement")
public class DepartementController {
    private DepartementService departementService;

    @GetMapping("/all")
    public List<DepartementResponseDTO> getAllDepartements(){
        return departementService.getDepartements();
    }

    @GetMapping("/{id}")
    public DepartementResponseDTO getDepartement(@PathVariable Long id) throws DepartementNotFoundException {
        if(id!=null) return departementService.getDepartement(id);
        return null;
    }

    @PostMapping("/add")
    public DepartementResponseDTO createDepartement(@RequestBody DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException {
        if(departementRequestDTO!=null) return departementService.createDepartement(departementRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public DepartementResponseDTO updateDepartement(@PathVariable Long id, @RequestBody DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException {
        if(departementRequestDTO!=null && id!=null)
            return departementService.updateDepartement(id, departementRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteDepartement(@PathVariable Long id) throws DepartementNotFoundException {
        if(id!=null) {
            departementService.deleteDepartement(id);
            return true;

        }else return false;
    }
}
