package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,String> {
}
