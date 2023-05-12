package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.InscriptionPedagogiqueRequestDTO;
import ma.enset.delibrations.dtos.responses.InscriptionpedagoqiqueResponseDTO;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.services.InscriptionpedagogiqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/inscriptionPedagogique")
public class InscriptionPedagogiqueController {
    private InscriptionpedagogiqueService inscriptionpedagogiqueService;

    @GetMapping("/all")
    public List<InscriptionpedagoqiqueResponseDTO>  getAllInscriptionsPedagogique(){
        return inscriptionpedagogiqueService.getInscriptionspedagogique();
    }

    @GetMapping("/{id}")
    public InscriptionpedagoqiqueResponseDTO getInscriptionPedagogique(@PathVariable Long id) throws InscriptionPedagogiqueNotFoundException {
        if(id!=null) return inscriptionpedagogiqueService.getInscriptionpedagogique(id);
        return null;
    }

    @PostMapping("/add")
    public InscriptionpedagoqiqueResponseDTO createInscriptionPedagogique(@RequestBody InscriptionPedagogiqueRequestDTO inscriptionPedagogiqueRequestDTO) throws ModuleNotFoundException, NoteElementNotFoundException, EtudiantNotFoundException {
        if(inscriptionPedagogiqueRequestDTO!=null) return inscriptionpedagogiqueService.createInscriptionpedagogique(inscriptionPedagogiqueRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public InscriptionpedagoqiqueResponseDTO updateInscriptionPedagogique(@PathVariable Long id, @RequestBody InscriptionPedagogiqueRequestDTO inscriptionPedagogiqueRequestDTO) throws ModuleNotFoundException, InscriptionPedagogiqueNotFoundException, NoteElementNotFoundException, NoteSemestreNotFoundException, EtudiantNotFoundException {
        if(inscriptionPedagogiqueRequestDTO!=null && id!=null) return inscriptionpedagogiqueService.updateInscriptionpedagogique(id,inscriptionPedagogiqueRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteInscriptionPedagogique(@PathVariable Long id) throws InscriptionPedagogiqueNotFoundException {
        if( id!=null)  inscriptionpedagogiqueService.deletegetInscriptionpedagogique(id);
    }

}
