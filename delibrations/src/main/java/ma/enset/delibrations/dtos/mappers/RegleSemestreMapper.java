package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.responses.RegleSemestreResponseDTO;
import ma.enset.delibrations.entities.RegleSemestre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RegleSemestreMapper {
    public RegleSemestreResponseDTO fromRegleSemestre(RegleSemestre regleSemestre){
        RegleSemestreResponseDTO regleSemestreResponseDTO = new RegleSemestreResponseDTO();
        BeanUtils.copyProperties(regleSemestre, regleSemestreResponseDTO);
        if(regleSemestre.getRegleCalcul()!=null)
        regleSemestreResponseDTO.setIdRegleCalcul(regleSemestre.getRegleCalcul().getId());
        return  regleSemestreResponseDTO;
    }
}
