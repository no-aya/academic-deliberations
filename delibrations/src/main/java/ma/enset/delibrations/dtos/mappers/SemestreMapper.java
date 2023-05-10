package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.entities.Professeur;
import ma.enset.delibrations.entities.Semestre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SemestreMapper {
    //RequestDTO Entity

    public SemestreRequestDTO fromEntitytoRequestDTO(Semestre semestre){
        SemestreRequestDTO semestreRequestDTO = new SemestreRequestDTO();
        semestreRequestDTO.setNoteSemestres(
                semestre.getNoteSemestres()
                        .stream()
                        .map(NoteSemestre::getId
                        ).toArray(Long[]::new)
        );
        BeanUtils.copyProperties(semestre, semestreRequestDTO);
        return  semestreRequestDTO;
    }
    public Semestre fromRequestDTOtoEntity(SemestreRequestDTO semestreRequestDTO){
        Semestre semestre = new Semestre();
        BeanUtils.copyProperties(semestreRequestDTO, semestre);
        Long[] noteSemestresIds = semestreRequestDTO.getNoteSemestres();
        List<NoteSemestre> noteSemestres = new ArrayList<>();
        if (noteSemestresIds != null) {
            for (Long noteSemestreId : noteSemestresIds) {
                NoteSemestre noteSemestre = new NoteSemestre();
                noteSemestre.setId(noteSemestreId);
                noteSemestres.add(noteSemestre);
            }
        }
        semestre.setNoteSemestres(noteSemestres);
        return  semestre;
    }

    //ResponseDTO Entity
    public SemestreResponseDTO fromEntityToResponseDTO(Semestre semestre){
        SemestreResponseDTO semestreResponseDTO = new SemestreResponseDTO();

        BeanUtils.copyProperties(semestre, semestreResponseDTO);
        semestreResponseDTO.setNoteSemestres(
                semestre.getNoteSemestres()
                        .stream()
                        .map(NoteSemestre::getId
                        ).toArray(Long[]::new)
        );
        return  semestreResponseDTO;
    }


    public Semestre fromResponseDTOtoEntity(SemestreResponseDTO semestreResponseDTO){
        Semestre semestre = new Semestre();
        BeanUtils.copyProperties(semestreResponseDTO, semestre);
        Long[] noteSemestreIds = semestreResponseDTO.getNoteSemestres();
        for (Long noteSemestreId : noteSemestreIds) {
            NoteSemestre noteSemestre = new NoteSemestre();
            noteSemestre.setId(noteSemestreId);
            semestre.getNoteSemestres().add(noteSemestre);
        }
        return  semestre;
    }
}
