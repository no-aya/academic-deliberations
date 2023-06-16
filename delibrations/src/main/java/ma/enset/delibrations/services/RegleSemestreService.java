package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.RegleSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleSemestreResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleSemestreNotFoundException;

import java.util.List;

public interface RegleSemestreService {

    RegleSemestreResponseDTO createRegleSemestre(RegleSemestreRequestDTO regleSemestreRequestDTO) throws RegleCalculNotFoundException;

    RegleSemestreResponseDTO updateRegleSemestre(Long id, RegleSemestreRequestDTO regleSemestreRequestDTO) throws RegleSemestreNotFoundException, CannotProceedException;

    RegleSemestreResponseDTO getRegleSemestre(Long id) throws RegleSemestreNotFoundException;

    List<RegleSemestreResponseDTO> getReglesSemestre();

    void deleteRegleSemestre(Long id);
}
