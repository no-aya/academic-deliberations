package ma.enset.delibrations.dtos.responses;

import lombok.Data;
@Data
public class NoteSemestreResponseDTO {
    private Long id;
    private Float noteSession1;
    private Float noteSession2;
    //id du semestre concerné
    private Long semestre;
}
