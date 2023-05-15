package ma.enset.delibrations.services;






import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.exceptions.NoteModuleNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;

import java.util.List;


public interface ModuleService {
    ModuleResponseDTO createModule(ModuleRequestDTO moduleDTO ) throws CannotProceedException, NoteModuleNotFoundException, javax.naming.CannotProceedException;

    ModuleResponseDTO updateModule(Long id, ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException,NoteModuleNotFoundException, SemestreNotFoundException;

    ModuleResponseDTO getModuleByCode(String code);

    ModuleResponseDTO getModule (Long id) throws ModuleNotFoundException;
    List<ModuleResponseDTO> getModules();
    void deleteModule(Long id) throws ModuleNotFoundException;
}

