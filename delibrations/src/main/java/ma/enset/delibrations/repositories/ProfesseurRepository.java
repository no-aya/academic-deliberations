package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {
    Professeur findByNom(String nom);

}
