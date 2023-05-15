package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.Departement;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.Filiere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class FiliereMapper {
    public FiliereResponseDTO fromEntityToResponseDTO(Filiere filiere){
        FiliereResponseDTO filiereResponseDTO = new FiliereResponseDTO();
        BeanUtils.copyProperties(filiere, filiereResponseDTO);
        return  filiereResponseDTO;
    }

    public Filiere fromResponseDTOtoEntity(FiliereResponseDTO filiereResponseDTO){
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(filiereResponseDTO, filiere);
        return filiere;
    }

    //FiliereRequestDTO
    public FiliereRequestDTO fromEntityToRequestDTO(Filiere filiere){
        FiliereRequestDTO filiereRequestDTO = new FiliereRequestDTO();
        BeanUtils.copyProperties(filiere, filiereRequestDTO);
        return  filiereRequestDTO;
    }

    public Filiere fromRequestDTOtoEntity(FiliereRequestDTO filiereRequestDTO){
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(filiereRequestDTO, filiere);
        filiere.setCreatedAt(new Date());
        if(filiereRequestDTO.getDepartementId()!=null){
            Departement departement = new Departement();
            departement.setId(filiereRequestDTO.getDepartementId());
            filiere.setDepartement(departement);
        }
        return  filiere;
    }
}
