package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.InscriptionPedagogiqueRequestDTO;
import ma.enset.delibrations.dtos.responses.InscriptionpedagoqiqueResponseDTO;
import ma.enset.delibrations.exceptions.*;

import java.util.List;

public interface InscriptionpedagogiqueService {
    InscriptionpedagoqiqueResponseDTO createInscriptionpedagogique(InscriptionPedagogiqueRequestDTO inscriptionPedagogiqueRequestDTO) throws EtudiantNotFoundException, ModuleNotFoundException, NoteElementNotFoundException, NoteModuleNotFoundException;
    InscriptionpedagoqiqueResponseDTO updateInscriptionpedagogique(Long id, InscriptionPedagogiqueRequestDTO inscriptionPedagogiqueRequestDTO) throws InscriptionPedagogiqueNotFoundException, EtudiantNotFoundException, ModuleNotFoundException, NoteElementNotFoundException, NoteSemestreNotFoundException, NoteModuleNotFoundException;
    InscriptionpedagoqiqueResponseDTO getInscriptionpedagogique(Long id) throws InscriptionPedagogiqueNotFoundException;
    List<InscriptionpedagoqiqueResponseDTO> getInscriptionspedagogique();
    void deletegetInscriptionpedagogique(Long id) throws InscriptionPedagogiqueNotFoundException;

    // List<InscriptionpedagoqiqueResponseDTO> getInscriptionspedagogique(); by etudiant

}
