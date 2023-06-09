package ma.enset.delibrations.controllers;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.exceptions.NoteModuleNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.services.ModuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/module")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModuleController {
    private ModuleService moduleService;

    @GetMapping("/all")
    public List<ModuleResponseDTO> getAllModules(ModuleRequestDTO moduleRequestDTO){
        if(moduleRequestDTO != null) return moduleService.getModules();
        return null;
    }
    @GetMapping("/{code}")
    public  ModuleResponseDTO getModule(@PathVariable String code) throws ModuleNotFoundException {
        if(code!=null)
            return  moduleService.getModule(code);
        return null;
    }
    @PostMapping("/add")
    public ModuleResponseDTO createModule(@RequestBody ModuleRequestDTO moduleRequestDTO) throws CannotProceedException, NoteModuleNotFoundException, javax.naming.CannotProceedException {
        if(moduleRequestDTO!=null)
            return moduleService.createModule(moduleRequestDTO);
        return null;
    }
    @PutMapping("/{code}")
    public ModuleResponseDTO updateModule(@PathVariable String code, @RequestBody ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException, NoteModuleNotFoundException, SemestreNotFoundException {
        if(moduleRequestDTO!=null && code!=null)
            return moduleService.updateModule(code,moduleRequestDTO);
        return null;
    }
    @DeleteMapping("/{code}")
    public void deleteModule(@PathVariable String  code)throws ModuleNotFoundException {
        if(code!=null)
            moduleService.deleteModule(code);
    }
}
