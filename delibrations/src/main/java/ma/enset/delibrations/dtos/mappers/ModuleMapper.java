package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.Semestre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ModuleMapper {

    //From RequestDTO to Entity
    public Module fromRequestDTOtoEntity(ModuleRequestDTO moduleRequestDTO){
        Module module = new Module();
        BeanUtils.copyProperties(moduleRequestDTO,module);
        Semestre semestre = new Semestre();
        semestre.setId(moduleRequestDTO.getSemestreId());
       module.setSemestre(semestre);
        return module;
    }

    //From Entity to ResponseDTO
    public ModuleResponseDTO fromEntitytoResponseDTO(Module module){
        ModuleResponseDTO moduleResponseDTO = new ModuleResponseDTO();
        BeanUtils.copyProperties(module,moduleResponseDTO);
        return moduleResponseDTO;
    }
}
