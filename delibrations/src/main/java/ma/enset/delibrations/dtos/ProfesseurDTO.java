package ma.enset.delibrations.dtos;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.delibrations.entities.Element;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfesseurDTO {
    private String cin;
    private String nom;
    private String prenom;
    private List<Element> elementModules = new ArrayList<>();

    public ProfesseurDTO(String cin, String nom, String prenom) {
            this.cin = cin;
            this.nom = nom;
            this.prenom = prenom;
    }

    //@OneToOne
    //private AppUser user;
    // From JWT
}
