package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Filiere findByCode(String codeFiliere);
    Filiere findFiliereById(Long id);
    Filiere findById(long id);
    Filiere findByIdAndSoftDeleteIsFalse(Long id);
    List<Filiere> findBySoftDeleteIsFalse();
    /*@Query("SELECT f FROM Filiere f JOIN FETCH f.departement d WHERE d.id = :cleEtrangere ")
    List<Filiere> findByCleEtrangere(Long cleEtrangere);*/
}
