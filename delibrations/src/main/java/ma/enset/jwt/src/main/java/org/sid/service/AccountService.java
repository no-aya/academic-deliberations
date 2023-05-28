package ma.enset.jwt.service;

import ma.enset.jwt.entities.AppRole;
import ma.enset.jwt.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(String username,String password,String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
}
