package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiliereResponseDTO {
    private Long id;
    private String code;
    private String intitule;
    private Long departementId;

    /*
        TODO add modules
     */

    private Date createdAt;
    private Date updatedAt;
}
