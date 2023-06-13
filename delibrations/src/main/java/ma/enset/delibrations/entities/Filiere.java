package ma.enset.delibrations.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Filiere {
        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        private Long id;
        private String code;
        private String intitule;
        @ManyToOne
        private Departement departement;
        @OneToMany(mappedBy = "filiere")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private List<Module> modules;

        @Temporal(TemporalType.DATE)
        private Date createdAt;
        @Temporal(TemporalType.DATE)
        private Date updatedAt;
        private Boolean softDelete=false;

        @ManyToOne
        @JoinColumn(name = "idRegleCalcul")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private RegleCalcul regleCalcul;

        @OneToMany(mappedBy = "filiere")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private List<Etudiant> etudiants;

        @OneToMany(mappedBy = "filiere")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private List<Semestre> semestres;

}
