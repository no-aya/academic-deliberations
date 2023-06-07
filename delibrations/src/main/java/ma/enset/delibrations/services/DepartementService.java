package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.exceptions.*;


import java.util.List;

public interface DepartementService {
    DepartementResponseDTO createDepartement(DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException;
    DepartementResponseDTO updateDepartement(String id, DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException;
   DepartementResponseDTO getDepartement(String id) throws  DepartementNotFoundException;
    List<DepartementResponseDTO> getDepartements();
    void deleteDepartement(String id) throws  DepartementNotFoundException;

    public List<DepartementResponseDTO> getDepartementsByProf(Long id) throws ProfesseurNotFoundException, ModuleNotFoundException, FiliereNotFoundException, DepartementNotFoundException;
}
