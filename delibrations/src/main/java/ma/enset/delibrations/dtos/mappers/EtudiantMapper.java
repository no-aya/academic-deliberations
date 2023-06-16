package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.entities.Etudiant;
import ma.enset.delibrations.entities.Filiere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    public EtudiantResponseDTO fromEtudiant(Etudiant etudiant) {
        EtudiantResponseDTO etudiantResponseDTO = new EtudiantResponseDTO();
        BeanUtils.copyProperties(etudiant, etudiantResponseDTO);
        etudiantResponseDTO.setFiliere(etudiant.getFiliere().getId());
        return etudiantResponseDTO;
    }


    public Etudiant fromRequestDTOtoEntity(EtudiantRequestDTO etudiantRequestDTO) {
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(etudiantRequestDTO, etudiant);
        Filiere filiere = new Filiere();
        filiere.setId(etudiantRequestDTO.getFiliereID());
        etudiant.setFiliere(filiere);
        return etudiant;
    }
}
