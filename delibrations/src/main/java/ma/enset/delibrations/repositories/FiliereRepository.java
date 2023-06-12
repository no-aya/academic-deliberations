package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Filiere findByCode(String codeFiliere);
    Filiere findByCodeAndSoftDeleteIsFalse(String codeFiliere);
    Filiere findFiliereById(Long id);
    Filiere findById(long id);
    Filiere findByIdAndSoftDeleteIsFalse(Long id);
    List<Filiere> findBySoftDeleteIsFalse();


}
