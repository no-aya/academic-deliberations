package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre,Long> {
    Semestre findByCode(String code);
    Semestre deleteByCode(String code);
}

