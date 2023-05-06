package ma.enset.delibrations.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Professeur {
        @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        private Long id;
        private String cin;
        private String nom;
        private String prenom;
        private String email;
        private String telephone;
        private String adresse;

        @OneToMany(mappedBy = "professeur")
        private List<Element> elementModules;

        public Professeur(String cin, String nom, String prenom) {
            this.cin = cin;
            this.nom = nom;
            this.prenom = prenom;
        }
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    private boolean softDelete = false;
    //@OneToOne
    //private AppUser user;
    // From JWT
}
