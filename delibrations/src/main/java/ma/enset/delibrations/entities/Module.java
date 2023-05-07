package ma.enset.delibrations.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class Module {
    @Id
    private String idModule;
    private String intitule;

    private boolean softDelete = false;
}
