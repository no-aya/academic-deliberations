package ma.enset.delibrations.services;






import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.exceptions.ModuleNotFoundException;

import java.util.List;


public interface ModuleService {
    ModuleResponseDTO createModule(ModuleRequestDTO moduleDTO );

    ModuleResponseDTO updateModule(String id, ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException;

   ModuleResponseDTO getModule (String id) throws ModuleNotFoundException;
    List<ModuleResponseDTO> getModules();
    void deleteModule(String id) throws ModuleNotFoundException;
}

