package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Departement;
import ma.enset.delibrations.entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement,Long> {

    Departement findByCode(String code);
    Departement findById(String Id);

    Departement findByIdAndSoftDeleteIsFalse(Long id);
    Departement findByCodeAndSoftDeleteIsFalse(String code);
    List<Departement> findBySoftDeleteIsFalse();

}
