package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    public EtudiantResponseDTO fromEtudiant(Etudiant etudiant){
        EtudiantResponseDTO etudiantResponseDTO = new EtudiantResponseDTO();
        BeanUtils.copyProperties(etudiant, etudiantResponseDTO);
        return  etudiantResponseDTO;
    }
}
