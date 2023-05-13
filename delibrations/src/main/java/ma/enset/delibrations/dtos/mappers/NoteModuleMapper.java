package ma.enset.delibrations.dtos.mappers;


import ma.enset.delibrations.dtos.requests.NoteModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteModuleResponseDTO;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.NoteModule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NoteModuleMapper {
    public NoteModuleResponseDTO fromEntitytoResponseDTO(NoteModule noteModule){
        NoteModuleResponseDTO noteModuleResponseDTO = new NoteModuleResponseDTO();
        BeanUtils.copyProperties(noteModule, noteModuleResponseDTO);
        noteModuleResponseDTO.setIdModule(noteModule.getModule().getIdModule());
        return  noteModuleResponseDTO;
    }

    public NoteModule fromResponseDTOtoEntity(NoteModuleResponseDTO noteModuleResponseDTO) {
        NoteModule noteModule = new NoteModule();
        BeanUtils.copyProperties(noteModuleResponseDTO, noteModule);
        Module module = new Module();
        module.setIdModule(noteModuleResponseDTO.getIdModule());
        noteModule.setModule(module);
        return noteModule;
    }


    public NoteModuleRequestDTO fromEntitytoRequestDTO(NoteModule noteModule){
        NoteModuleRequestDTO noteModuleRequestDTO = new NoteModuleRequestDTO();
        BeanUtils.copyProperties(noteModule, noteModuleRequestDTO);
        noteModuleRequestDTO.setIdModule(noteModule.getModule().getIdModule());
        return noteModuleRequestDTO;
    }

    public NoteModule fromRequestDTOtoEntity(NoteModuleRequestDTO noteModuleRequestDTO) {
        NoteModule noteModule = new NoteModule();
        BeanUtils.copyProperties(noteModuleRequestDTO, noteModule);
        Module module = new Module();
        module.setIdModule(noteModuleRequestDTO.getIdModule());
        noteModule.setModule(module);
        return noteModule;
    }
}
