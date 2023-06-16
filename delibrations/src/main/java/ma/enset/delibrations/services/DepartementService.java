package ma.enset.delibrations.services;


import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.entities.exceptions.*;


import java.util.List;

public interface DepartementService {
    DepartementResponseDTO createDepartement(DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException;
    DepartementResponseDTO updateDepartement(Long id, DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException;
   DepartementResponseDTO getDepartement(Long id) throws  DepartementNotFoundException;
    List<DepartementResponseDTO> getDepartements();
    void deleteDepartement(String id) throws  DepartementNotFoundException;
    public List<DepartementResponseDTO> getDepartementsByProf(Long idProf, String libelS, String s) throws ProfesseurNotFoundException, ModuleNotFoundException, FiliereNotFoundException, DepartementNotFoundException;

}
