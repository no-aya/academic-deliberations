package ma.enset.delibrations.dtos.responses;

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
public class EtudiantResponseDTO {
    private Long id;
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
    private Long filiere;

    private Date createdAt; //date creation de l'etudiant dans la base de donnée
    private Date updatedOn; //date modification de l'un des attributs de l'étudiant dans la BD
}
