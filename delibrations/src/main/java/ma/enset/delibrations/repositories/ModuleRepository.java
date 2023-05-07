package ma.enset.delibrations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.enset.delibrations.entities.Module;

public interface ModuleRepository extends JpaRepository<Module,String> {
}
