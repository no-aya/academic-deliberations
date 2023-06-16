package ma.enset.delibrations.dtos.responses;



import lombok.Data;

import java.util.Date;

@Data
public class NoteModuleResponseDTO {
    private Long id;
    private Double noteSession1;
    private Double noteSession2;
    private Long idModule;
    private String statut; //enum

    private Date createdAt;
    private Date updatedOn;
}
