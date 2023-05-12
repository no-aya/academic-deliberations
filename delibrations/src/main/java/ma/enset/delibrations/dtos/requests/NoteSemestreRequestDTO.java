package ma.enset.delibrations.dtos.requests;

import lombok.Data;

@Data
public class NoteSemestreRequestDTO {
    private Float noteSession1;
    private Float noteSession2;
    private Long semestreId;

    /*Request form :
    {
        "id": 1,
        "noteSession1": 12,
        "noteSession2": 12,
        "semestreId": 1
    }
     */



}
