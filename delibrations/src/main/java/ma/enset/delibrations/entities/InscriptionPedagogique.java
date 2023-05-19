package ma.enset.delibrations.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionPedagogique {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean softDelete = false;
    private Date createdAt;
    private Date updatedOn;

    @ManyToOne @JoinColumn(name = "idEtudiant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Etudiant etudiant;

    @ManyToOne @JoinColumn(name = "idModule")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Module module;

    @ManyToOne @JoinColumn(name = "idNoteModule")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private NoteModule noteModule;

    @ManyToOne @JoinColumn(name = "idNoteElement")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private NoteElement noteElement;

    @ManyToOne @JoinColumn(name = "idNoteSemestre")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private NoteSemestre noteSemestre;

}
