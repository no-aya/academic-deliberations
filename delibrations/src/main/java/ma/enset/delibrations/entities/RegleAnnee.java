package ma.enset.delibrations.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegleAnnee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Float noteValidation;
    private Integer nombreModuleDerogation;
    private Float noteEliminatoire;

    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date updatedOn;

    private boolean softDelete = false;

    @ManyToOne
    @JoinColumn(name = "idRegleCalcul")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RegleCalcul regleCalcul;
}
