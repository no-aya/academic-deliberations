package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemestreResponseDTO {
    private Long id;
    private String code;
    private String libelle;

    private Long[] noteSemestres;

    private Long anneeUnivId;
/*
    private boolean sessionStatus = false;*/
}
