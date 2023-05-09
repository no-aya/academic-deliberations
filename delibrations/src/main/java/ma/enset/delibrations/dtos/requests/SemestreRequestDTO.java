package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemestreRequestDTO {
    private Long id;
    private String code;
    private String libelle;
    private Long[] noteSemestres;
}
