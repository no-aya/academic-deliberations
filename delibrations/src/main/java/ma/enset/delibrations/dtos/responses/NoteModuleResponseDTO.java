package ma.enset.delibrations.dtos.responses;



import lombok.Data;

import java.util.Date;

@Data
public class NoteModuleResponseDTO {

    private Long id;
    private Float noteSession1;
    private Float noteSession2;
    private Long idModule;

    private Date createdAt;
    private Date updatedOn;
}
