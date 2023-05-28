package ma.enset.delibrations.security.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.security.AppUser;
import ma.enset.delibrations.security.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")

public class AppUserController {

    private AppUserService appUserService;

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/users/search")
    public List<AppUser> searchUser(@RequestParam(name = "keyword",defaultValue = "") String keyword) {
        return appUserService.searchUser("%"+keyword+"%");
    }


    @PostMapping("/users")
    public AppUser saveUser(@RequestBody AppUser user) {
        return appUserService.addNewUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id)  {
        appUserService.delete(id);
    }

    @PutMapping("/users/{id}")
    public AppUser updateUser(@PathVariable Long id,@RequestBody AppUser appUser) {
        appUser.setId(id);
        return appUserService.update(appUser);
    }



}
