package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.services.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departements")
@CrossOrigin("*")
public class DepartementController {
    private DepartementService departementService;

    @GetMapping("/all")
    public List<DepartementResponseDTO> getAllDepartements(){
        return departementService.getDepartements();
    }

    @GetMapping("/departement/{code}")
    public DepartementResponseDTO getDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) return departementService.getDepartement(code);
        return null;
    }

    @PostMapping("/add")
    public DepartementResponseDTO createDepartement(@RequestBody DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null) return departementService.createDepartement(departementRequestDTO);
        return null;
    }

    @PutMapping("/{code}")
    public DepartementResponseDTO updateDepartement(@PathVariable String code, @RequestBody DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
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


    @GetMapping("/{id}")
    public List<DepartementResponseDTO> getDepartementsByProf(@PathVariable Long id) throws ProfesseurNotFoundException, ModuleNotFoundException, FiliereNotFoundException, DepartementNotFoundException {
        if(id!=null) {
            return  departementService.getDepartementsByProf(id);
        }else return null;
    }
 
}
