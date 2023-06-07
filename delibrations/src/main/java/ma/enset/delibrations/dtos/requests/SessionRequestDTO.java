package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequestDTO {
    private String Libelle;
    boolean isClosed;
    Date dateDebut;
    Date dateFin;
    Long semestreId;
}
