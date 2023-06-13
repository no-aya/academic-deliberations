package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;

import ma.enset.delibrations.entities.Departement;
import ma.enset.delibrations.entities.Filiere;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DepartementMapper {
    public DepartementResponseDTO fromEntityToResponseDTO(Departement departement){
        DepartementResponseDTO departementResponseDTO = new DepartementResponseDTO();
        BeanUtils.copyProperties(departement, departementResponseDTO);
        if(departement.getFilieres()!=null) {
            departementResponseDTO.setFilieres(
                    departement.getFilieres()
                            .stream()
                            .map(Filiere::getId
                            ).toArray(Long[]::new)
            );
        }
        return  departementResponseDTO;
    }

    public Departement fromResponseDTOtoEntity(DepartementResponseDTO departementResponseDTO){
        Departement departement = new Departement();
        BeanUtils.copyProperties(departementResponseDTO, departement);
        Long[] filiereIds = departementResponseDTO.getFilieres();
        for (Long filiereID : filiereIds) {
            Filiere filiere = new Filiere();
            filiere.setId(filiereID);
            departement.getFilieres().add(filiere);
        }
        return departement;
    }

    //DepartementRequestDTO
    public DepartementRequestDTO fromEntitytoRequestDTO(Departement departement){
        DepartementRequestDTO departementRequestDTO = new DepartementRequestDTO();
        departementRequestDTO.setFilieres(
                departement.getFilieres()
                        .stream()
                        .map(Filiere::getId
                        ).toArray(Long[]::new)
        );
        BeanUtils.copyProperties(departement, departementRequestDTO);
        return  departementRequestDTO;
    }

    public Departement fromRequestDTOtoEntity(DepartementRequestDTO departementRequestDTO){
        Departement departement = new Departement();

        BeanUtils.copyProperties(departementRequestDTO, departement);
        Long[] filiereIds = departementRequestDTO.getFilieres();
        System.out.println("filiereIds = " + Arrays.toString(filiereIds));
        List<Filiere> departementFilieres = new ArrayList<>();
        if (filiereIds != null) {
            for (Long filiereId : filiereIds) {
                Filiere filiere = new Filiere();
                filiere.setId(filiereId);
                departementFilieres.add(filiere);
            }
            departement.setFilieres(departementFilieres);
        }
        return  departement;
    }
}
