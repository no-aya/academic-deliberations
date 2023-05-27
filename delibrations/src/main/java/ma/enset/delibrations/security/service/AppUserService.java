package ma.enset.delibrations.security.service;

import ma.enset.delibrations.security.AppUser;

import java.util.List;

public interface AppUserService {

    //CRUD AppUser
    AppUser addNewUser(AppUser appUser);

    AppUser update(AppUser appUser);
     void delete(AppUser appUser);

    void suspend(AppUser appUser);

    //get all users
    List<AppUser> getAllUsers();

    //get user by username
    AppUser loadUserByUsername( String username);

    //search user by keyword
    List<AppUser> searchUser(String keyword);



}
