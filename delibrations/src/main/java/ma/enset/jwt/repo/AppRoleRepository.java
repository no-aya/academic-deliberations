package ma.enset.jwt.repo;

import ma.enset.jwt.entities.AppRole;
import ma.enset.jwt.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
