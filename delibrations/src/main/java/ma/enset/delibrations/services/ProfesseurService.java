package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.exceptions.ProfesseurNotFoundException;

import java.util.List;

public interface ProfesseurService {

    //id est le cin
    ProfesseurResponseDTO createProfesseur(ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException, ProfesseurNotFoundException, ElementNotFoundException;
    ProfesseurResponseDTO updateProfesseur(String id, ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException, ElementNotFoundException;
    ProfesseurResponseDTO getProfesseur(String id) throws ProfesseurNotFoundException;
    List<ProfesseurResponseDTO> getProfesseurs();
    void deleteProfesseur(String id) throws ProfesseurNotFoundException;
}
