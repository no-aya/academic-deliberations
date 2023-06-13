package ma.enset.delibrations.services;






import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.SemestreNotFoundException;

import java.util.List;


public interface ModuleService {
    ModuleResponseDTO createModule(ModuleRequestDTO moduleDTO ) throws CannotProceedException, NoteModuleNotFoundException, javax.naming.CannotProceedException;

    ModuleResponseDTO updateModule(String id, ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException,NoteModuleNotFoundException, SemestreNotFoundException;

    ModuleResponseDTO getModuleByCode(String code);

    ModuleResponseDTO getModule (String id) throws ModuleNotFoundException;
    List<ModuleResponseDTO> getModules();
    void deleteModule(String id) throws ModuleNotFoundException;
    List<ModuleResponseDTO> getModuleWithFiliereAndProf(Long idProf, Long idFiliere, String libelS) throws ModuleNotFoundException;

}

