package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementRequestDTO {
    private Long id;
    private String code;
    private String intitule;
    private Long[] filieres;
    /*
        add chef departement Id
     */
}
