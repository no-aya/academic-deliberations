package ma.enset.delibrations.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.delibrations.entities.enums.Sexe;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor  @NoArgsConstructor @Builder
public class Etudiant {
    @Id
    private String id;
    private String apogee;
    private String cin;
    private String nom;
    private String prenom;
    private String photo;
    private String email;
    private String password;
    private String telephone;
    private String adresse;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    private Date createdAt; //date creation de l'etudiant dans la base de donnée
    private Date updatedOn; //date modification de l'un des attributs de l'étudiant dans la BD

    private boolean softDelete = false; //Dans le cas de suppression d'un etudiant on va pas le supprimer definitivement dans la BD. On va le garder au niveau de la BD mais on va pas l'afficher au niveau front end


}
