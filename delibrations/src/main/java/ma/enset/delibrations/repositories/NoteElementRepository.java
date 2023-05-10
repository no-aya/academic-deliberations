package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.NoteElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteElementRepository extends JpaRepository<NoteElement, Long> {
}
