package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.InscriptionPedagogique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscriptionPedagogiqueRepository extends JpaRepository<InscriptionPedagogique,Long> {
    InscriptionPedagogique findByIdAndSoftDeleteIsFalse(Long id);

    List<InscriptionPedagogique> findBySoftDeleteIsFalse();

    @Query("SELECT i FROM InscriptionPedagogique i JOIN FETCH i.module m WHERE m.id = :cleEtrangere ")
    List<InscriptionPedagogique> findByCleEtrangereModule(Long cleEtrangere);

    @Query("SELECT i FROM InscriptionPedagogique i JOIN FETCH i.noteElement n WHERE n.id = :cleEtrangere ")
    InscriptionPedagogique findByCleEtrangereNoteElement(Long cleEtrangere);

    @Query("SELECT i FROM InscriptionPedagogique i JOIN FETCH i.noteModule n WHERE n.id = :cleEtrangere ")
    List<InscriptionPedagogique> findByCleEtrangereNoteModule(Long cleEtrangere);
}
