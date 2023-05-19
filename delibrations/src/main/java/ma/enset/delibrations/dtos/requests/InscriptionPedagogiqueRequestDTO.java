package ma.enset.delibrations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionPedagogiqueRequestDTO {
    private String idEtudiant;
    private String idModule;
    private Long idNoteElement;
    private Long idNoteModule;
    private Long idNoteSemestre;
}
