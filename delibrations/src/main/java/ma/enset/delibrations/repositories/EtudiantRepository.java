package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Etudiant findByIdAndSoftDeleteIsFalse(Long id);
    Etudiant findByApogeeAndSoftDeleteIsFalse(String code);
    List<Etudiant> findBySoftDeleteIsFalse();
    @Query("SELECT e FROM Etudiant e WHERE e.filiere.id = :filiereId")
    List<Etudiant> findByFiliereId(@Param("filiereId") Long filiereId);
}
