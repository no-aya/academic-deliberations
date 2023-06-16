package ma.enset.delibrations.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElementResponseDTO {
    private Long id;
    private String code;
    private String titre;
    private Float ponderation;
    private Long professeur;
    private Long moduleid;
}
