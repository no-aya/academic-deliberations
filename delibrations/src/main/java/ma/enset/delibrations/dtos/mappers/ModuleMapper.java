package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.entities.Module;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ModuleMapper {
    public ModuleResponseDTO fromModule(Module module){
        ModuleResponseDTO moduleResponseDTO = new ModuleResponseDTO();
        BeanUtils.copyProperties(module,moduleResponseDTO);
        return moduleResponseDTO;
    }
}
