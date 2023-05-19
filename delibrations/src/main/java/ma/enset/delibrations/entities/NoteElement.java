package ma.enset.delibrations.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteElement {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private double noteSession1;
    private double noteSession2;

    //Relation
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Element element;

    /*@OneToMany*/
    //TODO: Inscription pédagogique

    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;


    @OneToMany(mappedBy = "noteElement")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<InscriptionPedagogique> inscriptionPedagogiques;


}
