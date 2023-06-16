package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.RegleAnneeRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleAnneeResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleAnneeNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;

import java.util.List;

public interface RegleAnneeService {
    RegleAnneeResponseDTO createRegleAnnee(RegleAnneeRequestDTO regleAnneeRequestDTO) throws RegleCalculNotFoundException;

    RegleAnneeResponseDTO updateRegleAnnee(Long id, RegleAnneeRequestDTO regleAnneeRequestDTO) throws RegleAnneeNotFoundException, CannotProceedException;

    RegleAnneeResponseDTO getRegleAnnee(Long id) throws RegleAnneeNotFoundException;
    List<RegleAnneeResponseDTO> getReglesAnnee();
    void deleteRegleAnnee(Long id);

}
