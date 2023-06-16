package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.AnneeUnivRequestDTO;
import ma.enset.delibrations.dtos.responses.AnneeUnivResponseDTO;
import ma.enset.delibrations.entities.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.entities.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.services.AnneeUnivService;
import org.springframework.web.bind.annotation.*;

import javax.naming.CannotProceedException;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/annee-univ")
@CrossOrigin("*")
public class AnneeUnivController {

    private AnneeUnivService anneeUnivService;

    @GetMapping("/all")
    public List<AnneeUnivResponseDTO> getAllAnneeUnivs(){
        return anneeUnivService.getAnneeUnivs();
    }

    @GetMapping("/{id}")
    public AnneeUnivResponseDTO getAnneeUniv(@PathVariable Long id) throws AnneeUnivNotFoundException {
        AnneeUnivResponseDTO anneeUnivResponseDTO = anneeUnivService.getAnneeUniv(id);
        System.out.println(anneeUnivResponseDTO);
        if (id!=null) return anneeUnivResponseDTO;
        return null;
    }

    @PostMapping("/add")
    public AnneeUnivResponseDTO createAnneeUniv(@RequestBody AnneeUnivRequestDTO anneeUnivRequestDTO) throws CannotProceedException, NoteSemestreNotFoundException {
        if (anneeUnivRequestDTO!=null)return anneeUnivService.createAnneeUniv(anneeUnivRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public AnneeUnivResponseDTO updateAnneeUniv(@PathVariable Long id, @RequestBody AnneeUnivRequestDTO anneeUnivRequestDTO) throws AnneeUnivNotFoundException, ma.enset.delibrations.entities.exceptions.CannotProceedException, SemestreNotFoundException, NoteSemestreNotFoundException {
        if (id!=null && anneeUnivRequestDTO!=null)return anneeUnivService.updateAnneeUniv(id, anneeUnivRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteAnneeUniv(@PathVariable Long id) throws AnneeUnivNotFoundException {
        if (id!=null){
            AnneeUnivResponseDTO anneeUnivResponseDTO = anneeUnivService.getAnneeUniv(id);
            if (anneeUnivResponseDTO==null) throw new AnneeUnivNotFoundException(id);
            else anneeUnivService.deleteAnneeUniv(id);
            return true;
        }else return false;
    }

}
