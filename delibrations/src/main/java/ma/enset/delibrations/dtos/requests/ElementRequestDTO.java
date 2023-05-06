package ma.enset.delibrations.dtos.requests;


import lombok.Data;

@Data
public class ElementRequestDTO {
    private String code;
    private String titre;
    private Float ponderation;
    private ProfesseurRequestDTO professeur;

    //TODO: Add the other attributes "Filieres" and "Modules"
}
