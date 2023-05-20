package ma.enset.delibrations.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleCalcul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    private boolean softDelete = false;

    @OneToMany(mappedBy = "regleCalcul")
    private List<RegleSemestre> reglesSemestre;

    @OneToMany(mappedBy = "regleCalcul")
    private List<RegleAnnee> reglesAnnee;

    @OneToMany(mappedBy = "regleCalcul")
    private List<Filiere> filieres;

}
