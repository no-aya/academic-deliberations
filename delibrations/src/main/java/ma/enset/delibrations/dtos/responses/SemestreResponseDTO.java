package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemestreResponseDTO {
    private Long id;
    private String code;
    private String libelle;

    private Long[] noteSemestres;

    private Long anneeUnivId;
}
