package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.entities.Professeur;
import org.springframework.beans.BeanUtils;

public class ProfesseurMapper {

    //ProfesseurResponseDTO

    public ProfesseurResponseDTO fromEntityToResponseDTO(Professeur professeur){
        ProfesseurResponseDTO professeurResponseDTO = new ProfesseurResponseDTO();
        BeanUtils.copyProperties(professeur, professeurResponseDTO);
        return  professeurResponseDTO;
    }

    public Professeur fromResponseDTOtoEntity(ProfesseurResponseDTO professeurResponseDTO){
        Professeur professeur = new Professeur();
        BeanUtils.copyProperties(professeurResponseDTO, professeur);
        return  professeur;
    }

    //ProfesseurRequestDTO
    public ProfesseurRequestDTO fromEntitytoRequestDTO(Professeur professeur){
        ProfesseurRequestDTO professeurRequestDTO = new ProfesseurRequestDTO();
        BeanUtils.copyProperties(professeur, professeurRequestDTO);
        return  professeurRequestDTO;
    }

    public Professeur fromRequestDTOtoEntity(ProfesseurRequestDTO professeurRequestDTO){
        Professeur professeur = new Professeur();
        //TODO: Ids are not copied
        BeanUtils.copyProperties(professeurRequestDTO, professeur);
        return  professeur;
    }






}
