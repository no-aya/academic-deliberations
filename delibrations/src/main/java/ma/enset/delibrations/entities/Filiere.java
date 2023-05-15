package ma.enset.delibrations.entities;

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
        @OneToMany
        private List<Module> modules;
        /*
        TODO :  add Accreditation Date property
                add chef filiere (professor) after setting roles
        */
        @Temporal(TemporalType.DATE)
        private Date createdAt;
        @Temporal(TemporalType.DATE)
        private Date updatedAt;
        private Boolean softDelete=false;


}
