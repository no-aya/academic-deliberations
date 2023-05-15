package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.InscriptionPedagogique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionPedagogiqueRepository extends JpaRepository<InscriptionPedagogique,Long> {
    InscriptionPedagogique findByIdAndSoftDeleteIsFalse(Long id);

    List<InscriptionPedagogique> findBySoftDeleteIsFalse();
}
