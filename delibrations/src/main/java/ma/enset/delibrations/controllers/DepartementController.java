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

    @GetMapping("/{code}")
    public DepartementResponseDTO getDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) return departementService.getDepartement(code);
        return null;
    }

    @PostMapping("/add")
    public DepartementResponseDTO createDepartement(@RequestBody DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException {
        if(departementRequestDTO!=null) return departementService.createDepartement(departementRequestDTO);
        return null;
    }

    @PutMapping("/{code}")
    public DepartementResponseDTO updateDepartement(@PathVariable String code, @RequestBody DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException {
        if(departementRequestDTO!=null && code!=null)
            return departementService.updateDepartement(code, departementRequestDTO);
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) {
            departementService.deleteDepartement(code);
            return true;

        }else return false;
    }
}
