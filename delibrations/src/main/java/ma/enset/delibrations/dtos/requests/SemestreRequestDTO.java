package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.delibrations.entities.AnneeUniv;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemestreRequestDTO {
    private String code;
    private String libelle;
    private Long[] noteSemestres;

    private Long anneeUnivId;

    /*private Long sessionId;*/

    /*Request syntax:
    {
        "id": 1,
        "code": "S1",
        "libelle": "Semestre 1",
        "noteSemestres": [
            1,
            2
        ]
    }
     */
}
