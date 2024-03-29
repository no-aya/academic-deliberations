package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.exceptions.*;


import java.util.List;

public interface FiliereService {
    FiliereResponseDTO createFiliere(FiliereRequestDTO departementRequestDTO) throws CannotProceedException, FiliereNotFoundException, RegleCalculNotFoundException;
    FiliereResponseDTO updateFiliere(FiliereRequestDTO departementRequestDTO) throws FiliereNotFoundException, DepartementNotFoundException, RegleCalculNotFoundException;
    Filiere getFiliere(Long id) throws  FiliereNotFoundException;
    FiliereResponseDTO getFiliere(String code) throws FiliereNotFoundException;
    List<FiliereResponseDTO> getFilieres();
    void deleteFiliere(String code) throws  FiliereNotFoundException;
    List<FiliereResponseDTO> getFiliereWithDeptAndProf(Long idProf, Long idDept, String libelSemestre, String libelS) throws ModuleNotFoundException;


}
