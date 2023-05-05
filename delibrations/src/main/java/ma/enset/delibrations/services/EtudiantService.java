package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;

import java.util.List;

public interface EtudiantService {
    EtudiantResponseDTO createEtudiant(EtudiantRequestDTO etudiantRequestDTO);

    EtudiantResponseDTO updateEtudiant(String id, EtudiantRequestDTO etudiantRequestDTO);

    EtudiantResponseDTO getEtudiant(String id);

    List<EtudiantResponseDTO> getEtudiants();

    void deleteEtudiant(String id);

    //get Etudiant Par filliere..
}
