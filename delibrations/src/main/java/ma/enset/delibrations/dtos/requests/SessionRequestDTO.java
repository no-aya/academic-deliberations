package ma.enset.delibrations.dtos.requests;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionRequestDTO {
    Long id;
    String libelle;
    boolean isClosed;
    Date dateDebut;
    Date dateFin;
    Long semestreId;
}
