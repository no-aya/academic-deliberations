package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Etudiant findByIdAndSoftDeleteIsFalse(Long id);
    Etudiant findByApogeeAndSoftDeleteIsFalse(String code);
    List<Etudiant> findBySoftDeleteIsFalse();
}
