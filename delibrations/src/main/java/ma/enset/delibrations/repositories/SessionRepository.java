package ma.enset.delibrations.repositories;

import ma.enset.delibrations.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
