package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.InscriptionPedagogique;
import ma.enset.delibrations.entities.NoteModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteModuleRepository extends JpaRepository<NoteModule,Long> {
    @Query("SELECT nd FROM NoteModule nd JOIN FETCH nd.module m WHERE m.id = :cleEtrangere ")
    List<NoteModule> findByCleEtrangere(Long cleEtrangere);
}

