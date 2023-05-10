package ma.enset.delibrations.controllers;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.services.ModuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/module")
public class ModuleController {
    private ModuleService moduleService;

    @GetMapping("/all")
    public List<ModuleResponseDTO> getAllModules(ModuleRequestDTO moduleRequestDTO){
        if(moduleRequestDTO != null) return moduleService.getModules();
        return null;
    }
    @GetMapping("/{id}")
    public  ModuleResponseDTO getModule(@PathVariable String id) throws ModuleNotFoundException {
        if(id!=null)
            return  moduleService.getModule(id);
        return null;
    }
    @PostMapping
    public ModuleResponseDTO createModule(@RequestBody ModuleRequestDTO moduleRequestDTO){
        if(moduleRequestDTO!=null)
            return moduleService.createModule(moduleRequestDTO);
        return null;
    }
    @PutMapping("/{id}")
    public ModuleResponseDTO updateModule(@PathVariable String id, @RequestBody ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException{
        if(moduleRequestDTO!=null && id!=null)
            return moduleService.updateModule(id,moduleRequestDTO);
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable String id)throws ModuleNotFoundException {
        if(id!=null)
            moduleService.deleteModule(id);
    }
}
