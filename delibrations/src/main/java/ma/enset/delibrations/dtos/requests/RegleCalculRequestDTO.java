package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleCalculRequestDTO {
    private Long[] idFilieres;
    private Long[] idReglesSemeste;
    private Long[] idReglesAnnee;
}
