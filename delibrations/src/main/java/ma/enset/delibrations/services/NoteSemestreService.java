package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.NoteSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteSemestreResponseDTO;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.entities.exceptions.*;

import java.util.List;

public interface NoteSemestreService {

    NoteSemestreResponseDTO addNoteSemestre(NoteSemestreRequestDTO noteSemestreRequestDTO) throws SemestreNotFoundException;
    NoteSemestreResponseDTO updateNoteSemestre(NoteSemestreRequestDTO noteSemestreRequestDTO) throws ElementNotFoundException, ProfesseurNotFoundException, NoteSemestreNotFoundException, CannotProceedException, SemestreNotFoundException;
    void deleteNoteSemestre(Long id) throws  NoteSemestreNotFoundException;
    List<NoteSemestreResponseDTO> getAllNotesSemestre();
    NoteSemestreResponseDTO getNoteSemestre(Long id) throws NoteSemestreNotFoundException;

    NoteSemestre getNoteSemestreById(Long id) throws NoteSemestreNotFoundException;


}
