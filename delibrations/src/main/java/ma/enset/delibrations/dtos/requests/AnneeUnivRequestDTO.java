package ma.enset.delibrations.dtos.requests;

import lombok.Data;

import java.util.Date;

@Data
public class AnneeUnivRequestDTO {
    private String CodeAnnee;
    private Date DateDebut;
    private Date DateFin;

    private Long[] semestres;

}
