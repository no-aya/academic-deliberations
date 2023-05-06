package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
    Element findByCode(String code);
}
