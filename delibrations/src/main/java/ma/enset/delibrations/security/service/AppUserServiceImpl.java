package ma.enset.delibrations.security.service;

import ma.enset.delibrations.security.AppUser;
import ma.enset.delibrations.security.repository.AppUserRepository;

import java.util.List;

public class AppUserServiceImpl implements AppUserService{

    AppUserRepository appUserRepository;
    //PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(AppUser appUser) {
        //appUser.setPassword( passwordEncoder.encode( appUser.getPassword() ) );
        return appUserRepository.save( appUser);
    }

    @Override
    public AppUser update(AppUser appUser) {
        AppUser user = appUserRepository.findByUsername(appUser.getUsername());
        user.setUsername(appUser.getUsername());
        user.setLastname(appUser.getLastname());
        //user.setPassword( passwordEncoder.encode( appUser.getPassword() ) );
        user.setEmail(appUser.getEmail());
        return appUserRepository.save( user );
    }

    @Override
    public void delete(AppUser appUser) {
        AppUser user = appUserRepository.findByUsername(appUser.getUsername());
        appUserRepository.delete(user);
    }

    @Override
    public void suspend(AppUser appUser) {
        AppUser user = appUserRepository.findByUsername(appUser.getUsername());
        user.setSuspend(true);
    }

    @Override
    public List<AppUser> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return users;
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> searchUser(String keyword) {
        return appUserRepository.findByUsernameOrLastnameContains(keyword, keyword);
    }
}
