package ma.enset.delibrations.services.servicesImp;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.ModuleMapper;
import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.repositories.ModuleRepository;
import ma.enset.delibrations.services.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service @Transactional @AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private ModuleRepository moduleRepository;
    private ModuleMapper moduleMapper;

    @Override
    public ModuleResponseDTO createModule(ModuleRequestDTO moduleRequestDTO) {
       if(moduleRequestDTO!=null){
           Module module = moduleMapper.fromRequestDTOtoEntity(moduleRequestDTO);
           //TODO : Generate a unique id
           module.setIdModule(UUID.randomUUID().toString());
           moduleRepository.save(module);
           return  moduleMapper.fromEntitytoResponseDTO(module);
       }
       return null;
    }

    @Override
    public ModuleResponseDTO updateModule(String id,ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException {
        if(id != null && moduleRequestDTO != null) {
            Module module = moduleRepository.findByIdModuleAndSoftDeleteIsFalse(id);
            if (module == null) throw new ModuleNotFoundException(id);
            if (moduleRequestDTO.getIntitule() != null)
                    module.setIntitule(moduleRequestDTO.getIntitule());
            moduleRepository.save(module);
            return moduleMapper.fromEntitytoResponseDTO(module);
        }
        return null;
    }

    @Override
    public ModuleResponseDTO getModule(String id) throws ModuleNotFoundException {
        if(id == null) return null;
        Module moduleEtudiant = moduleRepository.findByIdModuleAndSoftDeleteIsFalse(id);
        if(moduleEtudiant == null) throw new ModuleNotFoundException(id);
        return moduleMapper.fromEntitytoResponseDTO(moduleEtudiant);
    }

    @Override
    public List<ModuleResponseDTO> getModules() {
        List<Module> moduleEtudiants = moduleRepository.findAll();
        List<ModuleResponseDTO> modulesResponse = new ArrayList<>();
        for(Module m : moduleEtudiants){
            ModuleResponseDTO responseDTO ;
            responseDTO = moduleMapper.fromEntitytoResponseDTO(m);
            modulesResponse.add(responseDTO);
        }
        return modulesResponse;
    }

    @Override
    public void deleteModule(String id) throws ModuleNotFoundException{
        Module module = moduleRepository.findByIdModuleAndSoftDeleteIsFalse(id);
        if(module == null) throw new ModuleNotFoundException(id);
        else
            module.setSoftDelete(true);
    }
}
