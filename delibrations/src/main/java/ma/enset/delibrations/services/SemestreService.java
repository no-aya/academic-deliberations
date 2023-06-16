package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.entities.Semestre;
import ma.enset.delibrations.entities.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.entities.exceptions.SemestreNotFoundException;

import javax.naming.CannotProceedException;
import java.util.List;

public interface SemestreService {

    SemestreResponseDTO createSemestre(SemestreRequestDTO semestreResponseDTO) throws CannotProceedException, NoteSemestreNotFoundException;
    SemestreResponseDTO updateSemestre(String id, SemestreRequestDTO semestreRequestDTO) throws SemestreNotFoundException, NoteSemestreNotFoundException, ma.enset.delibrations.entities.exceptions.CannotProceedException, AnneeUnivNotFoundException;
    SemestreResponseDTO getSemestre(String code) throws SemestreNotFoundException;
    void deleteSemestre(String id) throws SemestreNotFoundException;
    List<SemestreResponseDTO> getSemestres();
    SemestreResponseDTO getSemestreByCode(String code);

    Semestre getSemestre(Long id) throws SemestreNotFoundException;

}
