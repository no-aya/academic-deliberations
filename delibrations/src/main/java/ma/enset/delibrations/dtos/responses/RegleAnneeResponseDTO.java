package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleAnneeResponseDTO {
    private Long id;
    private Float noteValidation;
    private Integer nombreModuleDerogation;
    private Float noteEliminatoire;
    private Long idRegleCalcul;
    private Date createdAt;
    private Date updatedOn;
}
