package ma.enset.delibrations.security.mappers;

import ma.enset.delibrations.security.AppUser;
import org.springframework.beans.BeanUtils;

public class AppUserMapper {

    public AppUserResponseDTO fromEntityToResponseDTO(AppUser appUser){
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO();
        BeanUtils.copyProperties(appUser, appUserResponseDTO);
        return appUserResponseDTO;
    }

    public AppUser fromResponseDTOtoEntity(AppUserResponseDTO appUserResponseDTO){
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(appUserResponseDTO, appUser);
        return appUser;
    }

    public AppUserRequestDTO fromEntitytoRequestDTO(AppUser appUser){
        AppUserRequestDTO appUserRequestDTO = new AppUserRequestDTO();
        BeanUtils.copyProperties(appUser, appUserRequestDTO);
        return appUserRequestDTO;
    }

    public AppUser fromRequestDTOtoEntity(AppUserRequestDTO appUserRequestDTO){
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(appUserRequestDTO, appUser);
        return appUser;
    }




}
