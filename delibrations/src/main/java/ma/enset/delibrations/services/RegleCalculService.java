package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.RegleCalculRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleCalculResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;

import java.util.List;

public interface RegleCalculService {


    RegleCalculResponseDTO createRegleCalcul(RegleCalculRequestDTO regleCalculRequestDTO);

   // RegleCalculResponseDTO updateRegleCalcul(Long id, RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException;

    RegleCalculResponseDTO updateRegleCalculParAjout(Long id, RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException;

    RegleCalculResponseDTO updateRegleCalculParSuppression(Long id, RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException;

    RegleCalculResponseDTO getRegleCalcul(Long id) throws RegleCalculNotFoundException;

    List<RegleCalculResponseDTO> getReglesCalcul();

    void deleteRegleCalcul(Long id) throws RegleCalculNotFoundException;
}
