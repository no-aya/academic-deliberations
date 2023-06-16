package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.delibrations.entities.enums.Sexe;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EtudiantRequestDTO {
    private String cin;
    private String apogee;
    private String cne;
    private String nom;
    private String prenom;
    private String photo;
    private String email;
    private String password;
    private String telephone;
    private String adresse;
    private Date dateNaissance;
    private Sexe sexe;

}
