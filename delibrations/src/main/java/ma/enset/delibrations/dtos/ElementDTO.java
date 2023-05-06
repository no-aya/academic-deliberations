package ma.enset.delibrations.dtos;


import lombok.Data;

@Data
public class ElementDTO {
    private Long id;
    private String code;
    private String titre;
    private Float ponderation;
    private String professeur;
}
