package ma.enset.delibrations.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class NoteSemestre {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    //Session Normale
    private Float noteSession1;
    //Session  Rattrapage
    private Float noteSession2;

    //semestre peut avoir plusieurs noteSemestre
    @ManyToOne @JoinColumn(name = "idSemestre")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Semestre semestre;

    @OneToMany(mappedBy = "noteSemestre")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<InscriptionPedagogique> inscriptionPedagogiques;

}
