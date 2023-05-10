package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.NoteSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteSemestreResponseDTO;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.entities.Semestre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NoteSemestreMapper {

    public NoteSemestreRequestDTO fromEntitytoRequestDTO (NoteSemestre noteSemestre){
        NoteSemestreRequestDTO noteSemestreRequestDTO = new NoteSemestreRequestDTO();
        BeanUtils.copyProperties(noteSemestre, noteSemestreRequestDTO);
        //Change semestre to semestreId
        if (noteSemestre.getSemestre()!= null){
            noteSemestreRequestDTO.setSemestreId(noteSemestre.getSemestre().getId());
        }
        return noteSemestreRequestDTO;
    }

    public Semestre fromRequestDTOtoEntity (NoteSemestreRequestDTO noteSemestreRequestDTO){
        Semestre semestre = new Semestre();
        BeanUtils.copyProperties(noteSemestreRequestDTO, semestre);
        return semestre;
    }

    public NoteSemestreResponseDTO fromEntitytoResponseDTO (NoteSemestre noteSemestre){
        NoteSemestreResponseDTO noteSemestreResponseDTO = new NoteSemestreResponseDTO();
        BeanUtils.copyProperties(noteSemestre, noteSemestreResponseDTO);
        //Change Semestre to its id
        if (noteSemestre.getSemestre()!= null){
            noteSemestreResponseDTO.setSemestre(noteSemestre.getSemestre().getId());
        }
        return noteSemestreResponseDTO;
    }

    public Semestre fromResponseDTOtoEntity (NoteSemestreResponseDTO noteSemestreResponseDTO){
        Semestre semestre = new Semestre();
        BeanUtils.copyProperties(noteSemestreResponseDTO, semestre);
        return semestre;
    }

}
