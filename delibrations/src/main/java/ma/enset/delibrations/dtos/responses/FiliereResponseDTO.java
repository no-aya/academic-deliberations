package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiliereResponseDTO {
    private Long id;
    private String code;
    private String intitule;
    private Long departementId;
    private String departementIntitule;
    private Long regleCalculId;


    /*
        TODO add modules
     */

    private Date createdAt;
    private Date updatedAt;
}
