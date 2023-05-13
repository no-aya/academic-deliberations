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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModule;
    private String code;
    private String intitule;

    @OneToMany(mappedBy = "module")
    private List<NoteModule> noteModules;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Semestre semestre;


    private boolean softDelete = false;
}
