package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.RegleSemestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegleSemestreRepository extends JpaRepository<RegleSemestre,Long> {
    RegleSemestre findByIdAndSoftDeleteIsFalse(Long id);

}
