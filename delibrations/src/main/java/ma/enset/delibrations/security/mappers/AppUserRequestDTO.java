package ma.enset.delibrations.security.mappers;

import lombok.Data;

@Data
public class AppUserRequestDTO {

    private Long id;
    private String username;
    private String lastname;
    private String email;
    private String password;


}
