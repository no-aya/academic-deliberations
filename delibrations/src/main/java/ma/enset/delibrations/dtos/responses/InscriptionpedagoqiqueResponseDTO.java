package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionpedagoqiqueResponseDTO {
    private Long id;
    private Date createdAt; //date creation de l'etudiant dans la base de donnée
    private Date updatedOn; //date modification de l'un des attributs de l'étudiant dans la BD
}
