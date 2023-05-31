package ma.enset.delibrations.dtos.responses;


import jakarta.persistence.OneToOne;
import lombok.Data;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.security.AppUser;

import java.util.Date;
import java.util.List;

@Data
public class ProfesseurResponseDTO {
    private Long id;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private Long[] elementModules;

    private Date createdAt;
    private Date updatedOn;

    @OneToOne
    private AppUser user;
    // From JWT
}
