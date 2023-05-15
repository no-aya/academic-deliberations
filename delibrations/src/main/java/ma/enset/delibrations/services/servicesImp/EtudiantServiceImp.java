package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.EtudiantMapper;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.entities.Etudiant;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
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
    public EtudiantResponseDTO updateEtudiant(String id, EtudiantRequestDTO etudiantRequestDTO) throws EtudiantNotFoundException, CannotProceedException {
        if(id!=null && etudiantRequestDTO!=null) {
            Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(()->new EtudiantNotFoundException(id));
            if(etudiant!=null){
                etudiant.setUpdatedOn(new Date());
                if(etudiantRequestDTO.getCin()!=null) etudiant.setCin(etudiantRequestDTO.getCin());
                if(etudiantRequestDTO.getApogee()!=null) etudiant.setApogee(etudiantRequestDTO.getApogee());
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

                etudiantRepository.save(etudiant);
                return etudiantMapper.fromEtudiant(etudiant);
            }
            else try {
                throw new CannotProceedException("Cannot update Etudiant "+id);
            } catch (CannotProceedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new CannotProceedException("Cannot update Etudiant "+id);
    }

    @Override
    public EtudiantResponseDTO getEtudiant(String id) throws EtudiantNotFoundException {
            if(id==null) return null;

            Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(()->new EtudiantNotFoundException(id));
            return etudiantMapper.fromEtudiant(etudiant);
    }

    @Override
    public List<EtudiantResponseDTO> getEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();

        List<EtudiantResponseDTO> etudiantsResponse = new ArrayList<>();
        for (Etudiant e: etudiants) {
            EtudiantResponseDTO responseDTO;
            responseDTO = etudiantMapper.fromEtudiant(e);
            etudiantsResponse.add(responseDTO);
        }
        return etudiantsResponse;
    }

    @Override
    public void deleteEtudiant(String id) throws EtudiantNotFoundException {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(()-> new EtudiantNotFoundException(id));
        etudiant.setUpdatedOn(new Date());
        if(etudiant!= null) etudiant.setSoftDelete(true);
    }
}
