package ma.enset.delibrations.dtos.requests;

import lombok.Data;

@Data
public class NoteSemestreRequestDTO {

    private Long id;
    private Float noteSession1;
    private Float noteSession2;
    private Long semestreId;



}
