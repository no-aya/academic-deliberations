package ma.enset.delibrations.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Professeur {
        @Id
        private String cin;
        private String nom;
        private String prenom;
        @OneToMany
        private List<Element> elementModules;

        public Professeur(String cin, String nom, String prenom) {
            this.cin = cin;
            this.nom = nom;
            this.prenom = prenom;
        }
    private Date createdAt;
    private Date updatedOn;

    private boolean softDelete = false;
    //@OneToOne
    //private AppUser user;
    // From JWT
}
