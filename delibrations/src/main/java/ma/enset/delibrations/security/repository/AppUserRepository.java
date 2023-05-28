package ma.enset.delibrations.security.repository;

import ma.enset.delibrations.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    @Query("select u from AppUser u where u.username like :kw")
    List<AppUser> searchAppUser(@Param("kw") String keyword);


}
