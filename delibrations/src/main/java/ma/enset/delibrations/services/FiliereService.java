package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;


import java.util.List;

public interface FiliereService {
    FiliereResponseDTO createFiliere(FiliereRequestDTO departementRequestDTO) throws CannotProceedException, FiliereNotFoundException;
    FiliereResponseDTO updateFiliere(FiliereRequestDTO departementRequestDTO) throws FiliereNotFoundException, DepartementNotFoundException;
    Filiere getFiliere(Long id) throws  FiliereNotFoundException;
    FiliereResponseDTO getFiliere(String code) throws FiliereNotFoundException;
    List<FiliereResponseDTO> getFilieres();
    void deleteFiliere(String code) throws  FiliereNotFoundException;
}
