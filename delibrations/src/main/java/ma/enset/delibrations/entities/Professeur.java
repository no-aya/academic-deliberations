package ma.enset.delibrations.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.delibrations.security.AppUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Professeur {
        @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        private Long id;
        private String cin;
        private String nom;
        private String prenom;
        @Column(unique = true)
        private String email;
        private String telephone;
        private String adresse;

        //id User
        @OneToOne
        private AppUser idUser;

        @OneToMany(mappedBy = "professeur", fetch = FetchType.EAGER)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private List<Element> elementModules;

    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    private boolean softDelete = false;
    @OneToOne
    private AppUser user;
    // From JWT
}
