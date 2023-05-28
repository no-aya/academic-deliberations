package ma.enset.delibrations.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.jwt.entities.AppUser;

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

        @OneToMany(mappedBy = "professeur", fetch = FetchType.EAGER)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private List<Element> elementModules;

    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    private boolean softDelete = false;

    @OneToOne
    private AppUser appUser;
}
