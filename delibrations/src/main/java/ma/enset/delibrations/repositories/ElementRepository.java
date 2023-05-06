package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElementRepository extends JpaRepository<Element, String> {
    Element findByCode(String code);
}
