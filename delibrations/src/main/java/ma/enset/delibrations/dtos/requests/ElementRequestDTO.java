package ma.enset.delibrations.dtos.requests;


import lombok.Data;

@Data
public class ElementRequestDTO {
    private String code;
    private String titre;
    private Float ponderation;
    private Long professeurId;
    private Long moduleId;

    //TODO: Add the other attributes "Filieres" and "Modules"

}
