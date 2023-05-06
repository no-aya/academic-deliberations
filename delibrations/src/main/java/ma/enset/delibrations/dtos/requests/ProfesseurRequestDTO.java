package ma.enset.delibrations.dtos.requests;


import lombok.Data;
import ma.enset.delibrations.entities.Element;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfesseurRequestDTO {
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private Long[] elementModules;

    //@OneToOne
    //private AppUser user;
    // From JWT
}
