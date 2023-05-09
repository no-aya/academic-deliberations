package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;

import javax.naming.CannotProceedException;
import java.util.List;

public interface SemestreService {

    SemestreResponseDTO createSemestre(SemestreRequestDTO semestreResponseDTO) throws CannotProceedException;
    SemestreResponseDTO updateSemestre(Long id, SemestreRequestDTO semestreResponseDTO) throws SemestreNotFoundException, NoteSemestreNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException;
    SemestreResponseDTO getSemestre(Long id) throws SemestreNotFoundException;
    void deleteSemestre(Long id) throws SemestreNotFoundException;
    List<SemestreResponseDTO> getSemestres();
    SemestreResponseDTO getSemestreByCode(String code);


}
