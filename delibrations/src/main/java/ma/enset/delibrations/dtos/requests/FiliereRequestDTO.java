package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiliereRequestDTO {
    private Long id;
    private String code;
    private String intitule;
    private Long departementId;
    private Long regleCalculId;

}
