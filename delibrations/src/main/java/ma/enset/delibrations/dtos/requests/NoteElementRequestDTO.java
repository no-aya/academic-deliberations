package ma.enset.delibrations.dtos.requests;

import lombok.Data;

@Data
public class NoteElementRequestDTO {
    private Long id; //May be replaced with inscriptonPédaggiqueId
    private double noteSession1;
    private double noteSession2;
    private Long idElement;
    //TODO: Inscription pédagogique


    /*Request body example:
    {
        "id": 1,
        "noteSession1": 12,
        "noteSession2": 13,
        "idElement": 1
    }
    */
}
