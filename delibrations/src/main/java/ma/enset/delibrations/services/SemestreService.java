package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.entities.Semestre;
import ma.enset.delibrations.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;

import javax.naming.CannotProceedException;
import java.util.List;

public interface SemestreService {

    SemestreResponseDTO createSemestre(SemestreRequestDTO semestreResponseDTO) throws CannotProceedException, NoteSemestreNotFoundException;
    SemestreResponseDTO updateSemestre(Long id, SemestreRequestDTO semestreRequestDTO) throws SemestreNotFoundException, NoteSemestreNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException, AnneeUnivNotFoundException;
    SemestreResponseDTO getSemestre(String code) throws SemestreNotFoundException;
    void deleteSemestre(Long id) throws SemestreNotFoundException;
    List<SemestreResponseDTO> getSemestres();
    SemestreResponseDTO getSemestreByCode(String code);

    Semestre getSemestre(Long id) throws SemestreNotFoundException;

}
