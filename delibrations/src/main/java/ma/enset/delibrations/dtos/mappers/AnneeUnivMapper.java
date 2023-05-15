package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.AnneeUnivRequestDTO;
import ma.enset.delibrations.dtos.responses.AnneeUnivResponseDTO;
import ma.enset.delibrations.entities.AnneeUniv;
import ma.enset.delibrations.entities.Semestre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AnneeUnivMapper {

    public AnneeUnivResponseDTO fromEntityToResponseDTO(AnneeUniv anneeUniv){
        AnneeUnivResponseDTO anneeUnivResponseDTO = new AnneeUnivResponseDTO();
        /*convert elemnets to ids*/
        BeanUtils.copyProperties(anneeUniv, anneeUnivResponseDTO);
        anneeUnivResponseDTO.setSemestres(
                anneeUniv.getSemestres()
                        .stream()
                        .map(Semestre::getId
                        ).toArray(Long[]::new)
        );
        return  anneeUnivResponseDTO;
    }

    public AnneeUniv fromResponseDTOtoEntity(AnneeUnivResponseDTO anneeUnivResponseDTO){
        AnneeUniv anneeUniv = new AnneeUniv();
        BeanUtils.copyProperties(anneeUnivResponseDTO, anneeUniv);
        Long[] semestreIds = anneeUnivResponseDTO.getSemestres();
        for (Long semestreId : semestreIds) {
            Semestre semestre = new Semestre();
            semestre.setId(semestreId);
            anneeUniv.getSemestres().add(semestre);
        }
        return  anneeUniv;
    }

    public AnneeUniv fromRequestDTOtoEntity(AnneeUnivRequestDTO anneeUnivRequestDTO) {
        AnneeUniv anneeUniv = new AnneeUniv();
        BeanUtils.copyProperties(anneeUnivRequestDTO, anneeUniv);
        Long[] semestreIds = anneeUnivRequestDTO.getSemestres();
        List<Semestre> semestres = new ArrayList<>();
        if (semestreIds != null) {
            for (Long semestreId : semestreIds) {
                Semestre semestre = new Semestre();
                semestre.setId(semestreId);
                semestres.add(semestre);
            }
            anneeUniv.setSemestres(semestres);
        }
        return anneeUniv;
    }

    public AnneeUnivRequestDTO fromEntitytoRequestDTO(AnneeUniv anneeUniv) {
        AnneeUnivRequestDTO anneeUnivRequestDTO = new AnneeUnivRequestDTO();
        BeanUtils.copyProperties(anneeUniv, anneeUnivRequestDTO);
        anneeUnivRequestDTO.setSemestres(
                anneeUniv.getSemestres()
                        .stream()
                        .map(Semestre::getId
                        ).toArray(Long[]::new)
        );
        return anneeUnivRequestDTO;
    }




}
