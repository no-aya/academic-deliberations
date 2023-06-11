package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.InscriptionPedagogique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscriptionPedagogiqueRepository extends JpaRepository<InscriptionPedagogique,Long> {
    InscriptionPedagogique findByIdAndSoftDeleteIsFalse(Long id);

    List<InscriptionPedagogique> findBySoftDeleteIsFalse();

    @Query("SELECT i FROM InscriptionPedagogique i JOIN FETCH i.module m WHERE m.id = :cleEtrangere ")
    List<InscriptionPedagogique> findByCleEtrangere(Long cleEtrangere);
}
