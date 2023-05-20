package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleSemestreRequestDTO {
    private Float noteValidation;
    private Float noteCompensation;
    private Float noteEliminatoire;
    private Long idRegleCalcul;
}
