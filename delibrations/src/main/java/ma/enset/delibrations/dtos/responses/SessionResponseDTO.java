package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponseDTO {
    Long id;
    private String Libelle;
    boolean isClosed;
    Date dateDebut;
    Date dateFin;
    Long semestreId;
}
