package ma.enset.delibrations.dtos.requests;


import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.security.AppUser;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfesseurRequestDTO {
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private Long[] elementModules;

    @OneToOne
    private AppUser user;
    // From JWT
}
