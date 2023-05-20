package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.responses.RegleAnneeResponseDTO;
import ma.enset.delibrations.entities.RegleAnnee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RegleAnneeMapper {
    public RegleAnneeResponseDTO fromRegleAnnee(RegleAnnee regleAnnee){
        RegleAnneeResponseDTO regleAnneeResponseDTO = new RegleAnneeResponseDTO();
        BeanUtils.copyProperties(regleAnnee, regleAnneeResponseDTO);
        if(regleAnnee.getRegleCalcul()!=null) regleAnneeResponseDTO.setIdRegleCalcul(regleAnnee.getRegleCalcul().getId());
        return  regleAnneeResponseDTO;
    }
}
