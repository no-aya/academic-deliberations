package ma.enset.delibrations.repositories;

import ma.enset.delibrations.dtos.responses.ElementResponseDTO;
import ma.enset.delibrations.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementRepository extends JpaRepository<Element, Long> {
    Element findByCode(String code);
    @Query("SELECT e FROM Element e JOIN FETCH e.professeur c WHERE c.id = :cleEtrangere ")
    List<Element> findByCleEtrangere(Long cleEtrangere);
}
