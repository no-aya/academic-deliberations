package ma.enset.delibrations.dtos.requests;


import lombok.Data;

@Data
public class NoteModuleRequestDTO {
    private Long id;
    private Double noteSession1;
    private Double noteSession2;
    private Long idModule;
}


