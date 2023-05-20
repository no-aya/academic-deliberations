package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleAnneeRequestDTO {
    private Float noteValidation;
    private Integer nombreModuleDerogation;
    private Float noteEliminatoire;
    private Long idRegleCalcul;
}
