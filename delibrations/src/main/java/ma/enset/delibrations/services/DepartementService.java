package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;


import java.util.List;

public interface DepartementService {
    DepartementResponseDTO createDepartement(DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException;
    DepartementResponseDTO updateDepartement(String id, DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException;
   DepartementResponseDTO getDepartement(String id) throws  DepartementNotFoundException;
    List<DepartementResponseDTO> getDepartements();
    void deleteDepartement(String id) throws  DepartementNotFoundException;
}
