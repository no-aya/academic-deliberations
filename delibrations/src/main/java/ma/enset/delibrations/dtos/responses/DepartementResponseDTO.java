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
public class DepartementResponseDTO {
    private Long id;
    private String code;
    private String intitule;
    private Long[] filieres;

    /*
        add chef departement ID
     */

    private Date createdAt;
    private Date updatedAt;
}
