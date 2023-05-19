package ma.enset.delibrations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.enset.delibrations.entities.Module;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module,Long> {
    Module findByIdAndSoftDeleteIsFalse(Long id);
    Module findByCodeAndSoftDeleteIsFalse(String code);
    List<Module> findBySoftDeleteIsFalse();
    Module findByCode(String code);
}
