package ma.enset.delibrations.dtos.requests;

import lombok.Data;

@Data
public class NoteElementRequestDTO {
    private Long id; //May be replaced with inscriptonPédaggiqueId
    private double noteSession1;
    private double noteSession2;
    private Long idElement;
    //TODO: Inscription pédagogique
}
