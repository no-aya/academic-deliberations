package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.requests.ElementRequestDTO;
import ma.enset.delibrations.dtos.responses.ElementResponseDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.exceptions.*;
import ma.enset.delibrations.services.ElementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/elements")
@CrossOrigin("*")

public class ElementController {
    private ElementService elementService;

    @GetMapping("/all")
    public List<ElementResponseDTO> getAllElements(){
        return elementService.getAllElements();
    }


    @GetMapping("/{id}")
    public ElementResponseDTO getElement(@PathVariable String id) throws ElementNotFoundException {
       if (id!=null) return elementService.getElement(id);
       return null;
    }

    @PostMapping("/add")
    public ElementResponseDTO createElement(@RequestBody ElementRequestDTO elementRequestDTO) throws ProfesseurNotFoundException {
        return elementService.addElement(elementRequestDTO);
    }

    @PutMapping("/{id}")
    public ElementResponseDTO updateElement(@PathVariable String id, @RequestBody ElementRequestDTO elementRequestDTO) throws ElementNotFoundException, ProfesseurNotFoundException {
        if (id!=null) {
            elementRequestDTO.setCode(id);
            return elementService.updateElement(elementRequestDTO);
        }
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteElement(@PathVariable String code) throws ElementNotFoundException {
        if(code!=null) {
            elementService.deleteElement(code);
            return true;
        }
        return false;
    }

    @GetMapping
    public List<ElementResponseDTO> getFiliere(@RequestParam Long idProf, @RequestParam Long idModule, @RequestParam String codeAnnee,@RequestParam String libelS) throws FiliereNotFoundException, ModuleNotFoundException {
        if (idProf!=null && idModule!=null && codeAnnee!=null && libelS!=null) return elementService.getElementWithModuleAndProf(idProf, idModule, codeAnnee, libelS);
        return null;
    }
}
