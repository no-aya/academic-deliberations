package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.AnneeUnivRequestDTO;
import ma.enset.delibrations.dtos.responses.AnneeUnivResponseDTO;
import ma.enset.delibrations.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.services.AnneeUnivService;
import org.springframework.web.bind.annotation.*;

import javax.naming.CannotProceedException;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/annee-univ")
public class AnneeUnivController {

    private AnneeUnivService anneeUnivService;

    @GetMapping("/all")
    public List<AnneeUnivResponseDTO> getAllAnneeUnivs(){
        return anneeUnivService.getAnneeUnivs();
    }

    @GetMapping("/{id}")
    public AnneeUnivResponseDTO getAnneeUniv(Long id) throws AnneeUnivNotFoundException {
        if (id!=null)return anneeUnivService.getAnneeUniv(id);
        return null;
    }

    @PostMapping("/add")
    public AnneeUnivResponseDTO createAnneeUniv(AnneeUnivRequestDTO anneeUnivRequestDTO) throws CannotProceedException, NoteSemestreNotFoundException {
        if (anneeUnivRequestDTO!=null)return anneeUnivService.createAnneeUniv(anneeUnivRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public AnneeUnivResponseDTO updateAnneeUniv(Long id, AnneeUnivRequestDTO anneeUnivRequestDTO) throws AnneeUnivNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException, SemestreNotFoundException, NoteSemestreNotFoundException {
        if (id!=null && anneeUnivRequestDTO!=null)return anneeUnivService.updateAnneeUniv(id, anneeUnivRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteAnneeUniv(Long id) throws AnneeUnivNotFoundException {
        if (id!=null){
            anneeUnivService.deleteAnneeUniv(id);
            return true;
        }else return false;
    }

}
