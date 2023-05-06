package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;

import java.util.List;

public interface EtudiantService {
    EtudiantResponseDTO createEtudiant(EtudiantRequestDTO etudiantRequestDTO);
    EtudiantResponseDTO updateEtudiant(String id, EtudiantRequestDTO etudiantRequestDTO) throws EtudiantNotFoundException, CannotProceedException;
    EtudiantResponseDTO getEtudiant(String id) throws EtudiantNotFoundException;
    List<EtudiantResponseDTO> getEtudiants();
    void deleteEtudiant(String id) throws EtudiantNotFoundException;

    //get Etudiant Par filliere..
}
