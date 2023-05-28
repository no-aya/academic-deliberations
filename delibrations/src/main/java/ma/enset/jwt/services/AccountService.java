package ma.enset.jwt.services;

import ma.enset.jwt.entities.AppRole;
import ma.enset.jwt.entities.AppUser;
import ma.enset.jwt.repo.AppRoleRepository;
import ma.enset.jwt.repo.AppUserRepository;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    public AccountService(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    public AppUser newUser(AppUser appUser){
        return appUserRepository.save(appUser);
    }
    public AppRole newRole(AppRole appRole){
        return appRoleRepository.save(appRole);
    }
    public void addRoleToUser(String userName,String roleName){
        AppUser appUser=appUserRepository.findByUsername(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }
    public AppUser findByUserName(String userName){
        return appUserRepository.findByUsername(userName);
    }
}
