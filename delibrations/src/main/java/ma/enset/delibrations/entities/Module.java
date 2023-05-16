package ma.enset.delibrations.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    private String intitule;

    @OneToMany(mappedBy = "module")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<NoteModule> noteModules;

    @ManyToOne @JoinColumn(name = "idSemestre")
    private Semestre semestre;


    private boolean softDelete = false;

    @OneToMany(mappedBy = "module",fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Element> elements;


    @OneToMany(mappedBy = "module",fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<InscriptionPedagogique> inscriptionPedagogiques;

    @ManyToOne @JoinColumn(name = "idFiliere")
    Filiere filiere;
}
