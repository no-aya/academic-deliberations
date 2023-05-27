package ma.enset.delibrations.security.repository;

import ma.enset.delibrations.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    List<AppUser> findByUsernameOrLastnameContains(String keyword1, String keyword2);


}
