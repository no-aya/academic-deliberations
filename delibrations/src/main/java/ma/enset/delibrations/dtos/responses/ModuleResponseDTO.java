package ma.enset.delibrations.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponseDTO {
    private String code;
    private String intitule;
    private Long semestre;
}
