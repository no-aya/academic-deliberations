package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.AnneeUnivRequestDTO;
import ma.enset.delibrations.dtos.responses.AnneeUnivResponseDTO;
import ma.enset.delibrations.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;

import javax.naming.CannotProceedException;
import java.util.List;

public interface AnneeUnivService {

    AnneeUnivResponseDTO createAnneeUniv(AnneeUnivRequestDTO anneeUnivRequestDTO) throws CannotProceedException, NoteSemestreNotFoundException;
    AnneeUnivResponseDTO updateAnneeUniv(Long id, AnneeUnivRequestDTO anneeUnivRequestDTO) throws AnneeUnivNotFoundException, SemestreNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException, NoteSemestreNotFoundException;
    AnneeUnivResponseDTO getAnneeUniv(Long id) throws AnneeUnivNotFoundException;
    void deleteAnneeUniv(Long id) throws AnneeUnivNotFoundException;
    List<AnneeUnivResponseDTO> getAnneeUnivs();


}
