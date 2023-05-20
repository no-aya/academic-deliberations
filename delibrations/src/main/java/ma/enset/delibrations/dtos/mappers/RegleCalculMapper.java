package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.responses.RegleCalculResponseDTO;
import ma.enset.delibrations.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RegleCalculMapper {
    public RegleCalculResponseDTO fromRegleCalcul(RegleCalcul regleCalcul){
        RegleCalculResponseDTO regleCalculResponseDTO = new RegleCalculResponseDTO();
        BeanUtils.copyProperties(regleCalcul, regleCalculResponseDTO);
        regleCalculResponseDTO.setFilieres(
                regleCalcul.getFilieres()
                        .stream()
                        .map(Filiere::getId
                        ).toArray(Long[]::new)
        );
        regleCalculResponseDTO.setReglesAnnee(
                regleCalcul.getReglesAnnee()
                        .stream()
                        .map(RegleAnnee::getId
                        ).toArray(Long[]::new)
        );
        regleCalculResponseDTO.setReglesSemeste(
                regleCalcul.getReglesSemestre()
                        .stream()
                        .map(RegleSemestre::getId
                        ).toArray(Long[]::new)
        );
        return  regleCalculResponseDTO;
    }
}
