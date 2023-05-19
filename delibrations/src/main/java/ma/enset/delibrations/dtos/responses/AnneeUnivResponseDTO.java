package ma.enset.delibrations.dtos.responses;

import lombok.Data;

import java.util.Date;

@Data
public class AnneeUnivResponseDTO {
    private Long id;
    private String CodeAnnee;
    private Date DateDebut;
    private Date DateFin;
    private Long[] semestres;
}
