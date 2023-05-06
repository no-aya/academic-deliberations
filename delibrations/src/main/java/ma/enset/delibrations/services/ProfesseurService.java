package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ProfesseurNotFoundException;

import java.util.List;

public interface ProfesseurService {
    ProfesseurResponseDTO createProfesseur(ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException;
    ProfesseurResponseDTO updateProfesseur(Long id, ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException;
    ProfesseurResponseDTO getProfesseur(Long id) throws ProfesseurNotFoundException;
    List<ProfesseurResponseDTO> getProfesseurs();
    void deleteProfesseur(Long id) throws ProfesseurNotFoundException;
}
