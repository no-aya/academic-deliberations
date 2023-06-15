package ma.enset.delibrations.dtos.requests;

import lombok.Data;

@Data
public class NoteElementRequestDTO {
    private Long id; //May be replaced with inscriptonPédaggiqueId
    private Double noteSession1;
    private Double noteSession2;
    private Float coeficient;
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
