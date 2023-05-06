package ma.enset.delibrations.dtos.requests;


import lombok.Data;

@Data
public class ElementRequestDTO {
    private Long id;
    private String code;
    private String titre;
    private Float ponderation;
    private Long professeurId;

    //TODO: Add the other attributes "Filieres" and "Modules"
}
