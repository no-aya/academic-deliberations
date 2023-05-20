package ma.enset.delibrations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleCalculResponseDTO {
    private Long id;
    private Long[] filieres;
    private Long[] reglesSemeste;
    private Long[] reglesAnnee;


    private Date createdAt;
    private Date updatedOn;
}
