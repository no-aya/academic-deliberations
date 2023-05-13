package ma.enset.delibrations.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AnneeUniv {
    @Id
    private Long id;
    private String CodeAnnee;

    private Date DateDebut;
    private Date DateFin;

    @OneToMany(mappedBy = "anneeUniv", fetch = jakarta.persistence.FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Semestre> semestres;
}
