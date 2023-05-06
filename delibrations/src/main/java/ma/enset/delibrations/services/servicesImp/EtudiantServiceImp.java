package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.EtudiantMapper;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.entities.Etudiant;
import ma.enset.delibrations.repositories.EtudiantRepository;
import ma.enset.delibrations.services.EtudiantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional @AllArgsConstructor
public class EtudiantServiceImp implements EtudiantService {

    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;

    @Override
    public EtudiantResponseDTO createEtudiant(EtudiantRequestDTO etudiantRequestDTO) {

        if(etudiantRequestDTO!=null){
           Etudiant etudiant = new Etudiant();


            BeanUtils.copyProperties(etudiantRequestDTO,etudiant);
            etudiant.setId(UUID.randomUUID().toString());
            etudiant.setCreatedAt(new Date());

            etudiantRepository.save(etudiant);

            return etudiantMapper.fromEtudiant(etudiant);
        }

        return null;
    }

    @Override
    public EtudiantResponseDTO updateEtudiant(String id, EtudiantRequestDTO etudiantRequestDTO) {

        if(id!=null && etudiantRequestDTO!=null) {
            Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
            if(etudiant!=null){
                etudiant.setUpdatedOn(new Date());
                if(etudiantRequestDTO.getCin()!=null) etudiant.setCin(etudiantRequestDTO.getCin());
                if(etudiantRequestDTO.getCne()!=null) etudiant.setCne(etudiantRequestDTO.getCne());
                if(etudiantRequestDTO.getNom()!=null) etudiant.setNom(etudiantRequestDTO.getNom());
                if(etudiantRequestDTO.getPrenom()!=null) etudiant.setPrenom(etudiantRequestDTO.getPrenom());
                if(etudiantRequestDTO.getPhoto()!=null)etudiant.setPhoto(etudiantRequestDTO.getPhoto());
                if(etudiantRequestDTO.getEmail()!=null)  etudiant.setEmail(etudiantRequestDTO.getEmail());
                if(etudiantRequestDTO.getPassword()!=null)  etudiant.setPassword(etudiantRequestDTO.getPassword());
                if(etudiantRequestDTO.getTelephone()!=null)  etudiant.setTelephone(etudiantRequestDTO.getTelephone());
                if(etudiantRequestDTO.getAdresse()!=null) etudiant.setAdresse(etudiantRequestDTO.getAdresse());
                if(etudiantRequestDTO.getDateNaissance()!=null)  etudiant.setDateNaissance(etudiantRequestDTO.getDateNaissance());
                if(etudiantRequestDTO.getSexe()!=null)  etudiant.setSexe(etudiantRequestDTO.getSexe());
            }

            etudiantRepository.save(etudiant);
            return etudiantMapper.fromEtudiant(etudiant);
        }

        return null;
    }

    @Override
    public EtudiantResponseDTO getEtudiant(String id) {
            if(id==null) return null;

            Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
            return etudiantMapper.fromEtudiant(etudiant);
    }

    @Override
    public List<EtudiantResponseDTO> getEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();

        List<EtudiantResponseDTO> etudiantsResponse = new ArrayList<>();
        for (Etudiant e: etudiants) {
            EtudiantResponseDTO responseDTO = new EtudiantResponseDTO();
            responseDTO = etudiantMapper.fromEtudiant(e);
            etudiantsResponse.add(responseDTO);
        }
        return etudiantsResponse;
    }

    @Override
    public void deleteEtudiant(String id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);

        if(etudiant!= null) etudiant.setSoftDelete(true);
    }
}
