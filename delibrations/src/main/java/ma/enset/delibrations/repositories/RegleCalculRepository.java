package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.RegleAnnee;
import ma.enset.delibrations.entities.RegleCalcul;
import ma.enset.delibrations.entities.RegleSemestre;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegleCalculRepository extends JpaRepository<RegleCalcul,Long> {
    RegleCalcul findByIdAndSoftDeleteIsFalse(Long id);
    boolean existsByIdAndFilieresId(Long regleCalculId, Long filiereId);
    boolean existsByIdAndReglesSemestreId(Long regleCalculId, Long regleSemestreId);
    boolean existsByIdAndReglesAnneeId(Long regleCalculId, Long regleAnneeId);

    //Pour recuperer la liste de filieres d'une regle de calcul
    @Query("SELECT r.filieres FROM RegleCalcul r WHERE r.id = :idRegleCalcul")
    List<Filiere> findFilieresById(Long idRegleCalcul);


    @Query("SELECT r.reglesAnne FROM RegleCalcul r WHERE r.id = :idRegleCalcul")
    List<RegleAnnee> findReglesAnneeById(Long idRegleCalcul);

    @Query("SELECT r.ReglesSemestre FROM RegleCalcul r WHERE r.id = :idRegleCalcul")
    List<RegleSemestre> findReglesSemestreById(Long idRegleCalcul);


}
