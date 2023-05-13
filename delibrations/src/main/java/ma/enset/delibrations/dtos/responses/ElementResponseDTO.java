package ma.enset.delibrations.dtos.responses;


import lombok.Data;

@Data
public class ElementResponseDTO {
    private String code;
    private String titre;
    private Float ponderation;
    private Long professeur;
    private Long moduleid;
}
