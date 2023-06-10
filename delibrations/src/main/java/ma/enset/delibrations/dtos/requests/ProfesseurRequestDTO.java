package ma.enset.delibrations.dtos.requests;


import jakarta.persistence.OneToOne;
import lombok.Data;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.security.AppUser;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfesseurRequestDTO {
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
    private Long[] elementModules;

    @OneToOne
    private AppUser user;
    // From JWT
}
