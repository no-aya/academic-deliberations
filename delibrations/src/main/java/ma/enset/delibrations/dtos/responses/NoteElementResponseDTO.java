package ma.enset.delibrations.dtos.responses;

import lombok.Data;

import java.util.Date;

@Data
public class NoteElementResponseDTO {
    private Long id; //May be replaced with inscriptonPédaggiqueId
    private Double noteSession1;
    private Double noteSession2;
    private Float coeficient;
    private Long idElement;

    //TODO: Inscription pédagogique

    private Date createdAt;
    private Date updatedOn;
}
