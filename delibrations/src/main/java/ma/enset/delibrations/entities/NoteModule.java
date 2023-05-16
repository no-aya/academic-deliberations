package ma.enset.delibrations.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteModule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float noteSession1;
    private Float noteSession2;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "idModule")
    private Module module;

    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    @OneToMany(mappedBy = "noteModule")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<InscriptionPedagogique> inscriptionPedagogiques;




}
