package ma.enset.delibrations.dtos.responses;

import lombok.Data;

@Data
public class AnneeUnivResponseDTO {
    private Long id;
    private String CodeAnnee;
    private String DateDebut;
    private String DateFin;
    private Long[] semestres;
}
