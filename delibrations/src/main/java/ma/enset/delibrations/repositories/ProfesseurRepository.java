package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {
    Professeur findByNom(String nom);
    Professeur findByIdAndSoftDeleteIsFalse(Long id);
    List<Professeur> findBySoftDeleteIsFalse();
}
