package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.EtudiantMapper;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.entities.Etudiant;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.InscriptionPedagogique;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.repositories.EtudiantRepository;
import ma.enset.delibrations.repositories.FiliereRepository;
import ma.enset.delibrations.repositories.InscriptionPedagogiqueRepository;
import ma.enset.delibrations.repositories.ModuleRepository;
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
    private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
    private ModuleRepository moduleRepository;
    private FiliereRepository filiereRepository;

    //Inscription pedagogique

    @Override
    public EtudiantResponseDTO createEtudiant(EtudiantRequestDTO etudiantRequestDTO) throws CannotProceedException {
        if(etudiantRequestDTO!=null){
           Etudiant etudiant = etudiantMapper.fromRequestDTOtoEntity(etudiantRequestDTO);
            etudiant.setCreatedAt(new Date());
            etudiantRepository.save(etudiant);
            return etudiantMapper.fromEtudiant(etudiant);
        }
        throw new CannotProceedException("Etudiant is null");
    }

    @Override
    public EtudiantResponseDTO updateEtudiant(String id, EtudiantRequestDTO etudiantRequestDTO) throws EtudiantNotFoundException, CannotProceedException {
        if(id!=null && etudiantRequestDTO!=null) {
            Etudiant etudiant = etudiantRepository.findByApogeeAndSoftDeleteIsFalse(id);
            if (etudiant == null) throw new EtudiantNotFoundException(id);
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
            Etudiant etudiant = etudiantRepository.findByApogeeAndSoftDeleteIsFalse(id);
            if(etudiant==null) throw new EtudiantNotFoundException(id);
            return etudiantMapper.fromEtudiant(etudiant);
    }

    @Override
    public List<EtudiantResponseDTO> getEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findBySoftDeleteIsFalse();
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
        Etudiant etudiant = etudiantRepository.findByApogeeAndSoftDeleteIsFalse(id);
        if(etudiant==null) throw new EtudiantNotFoundException(id);
        etudiant.setUpdatedOn(new Date());
            etudiant.setSoftDelete(true);
            etudiantRepository.save(etudiant);

    }

    @Override
    public List<EtudiantResponseDTO> getEtudiantsByInscriptionPedagogique(Long idModule) {
        if (idModule!=null){
            Module module = moduleRepository.findByIdAndSoftDeleteIsFalse(idModule);
            if(module!=null ){
                List<InscriptionPedagogique> inscriptions= inscriptionPedagogiqueRepository.findByCleEtrangereModule(idModule);
                if(inscriptions!=null){
                    List<EtudiantResponseDTO> respenses = new ArrayList<>();
                    for (InscriptionPedagogique i: inscriptions) {
                        Etudiant etu = etudiantRepository.findByIdAndSoftDeleteIsFalse(i.getEtudiant().getId());
                        if(etu!=null){
                            respenses.add(etudiantMapper.fromEtudiant(etu));}
                    }
                   return respenses;
                    }
                }
            }
        return null;
    }
}
