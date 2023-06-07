package ma.enset.delibrations.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Libelle;
    private boolean isClosed;
    Date dateDebut;
    Date dateFin;
    /*
    @OneToOne
    Semestre semestre;*/

}
