package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionPedagogiqueRequestDTO {
    private String idEtudiant;
    private Long idNoteElement;
    private String idNoteModule;
    private Long idNoteSemestre;
    private String idModule;
}
