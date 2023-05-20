package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.RegleAnnee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegleAnneeRepository extends JpaRepository<RegleAnnee,Long> {
    RegleAnnee findByIdAndSoftDeleteIsFalse(Long id);
}
