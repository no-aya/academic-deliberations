package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.RegleCalculMapper;
import ma.enset.delibrations.dtos.requests.RegleCalculRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleCalculResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.services.RegleCalculService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class RegleCalculServiceImp implements RegleCalculService {

    private RegleCalculRepository regleCalculRepository;
    private RegleCalculMapper regleCalculMapper;

    private RegleAnneeRepository regleAnneeRepository;
    private RegleSemestreRepository regleSemestreRepository;

    private FiliereRepository filiereRepository;

    @Override
    public RegleCalculResponseDTO createRegleCalcul(RegleCalculRequestDTO regleCalculRequestDTO) {
        if(regleCalculRequestDTO!=null){
            RegleCalcul regleCalcul = new RegleCalcul();
            regleCalcul.setCreatedAt(new Date());

            if(regleCalculRequestDTO.getIdReglesAnnee()!=null){
                List<RegleAnnee> reglesAnnee =new ArrayList<>();

                for (Long r : regleCalculRequestDTO.getIdReglesAnnee()) {
                    RegleAnnee regleAnnee  = regleAnneeRepository.findById(r).orElse(null);

                    if(regleAnnee!=null){
                        reglesAnnee.add(regleAnnee);
                        regleAnnee.setRegleCalcul(regleCalcul);
                        regleAnneeRepository.save(regleAnnee);
                    }
                }

                regleCalcul.setReglesAnnee(reglesAnnee);

            }

            if(regleCalculRequestDTO.getIdReglesSemeste()!=null){
                List<RegleSemestre> reglesSemestre = new ArrayList<>();
                for(Long r : regleCalculRequestDTO.getIdReglesSemeste()){
                    RegleSemestre regleSemestre = regleSemestreRepository.findById(r).orElse(null);
                    if(regleSemestre!=null){
                        reglesSemestre.add(regleSemestre);
                        regleSemestre.setRegleCalcul(regleCalcul);
                        regleSemestreRepository.save(regleSemestre);
                    }
                }

                regleCalcul.setReglesSemestre(reglesSemestre);
            }

            if(regleCalculRequestDTO.getIdFilieres()!=null){
                List<Filiere> filieres = new ArrayList<>();
                for(Long f : regleCalculRequestDTO.getIdFilieres()){
                    Filiere filiere = filiereRepository.findById(f).orElse(null);
                    if(filiere!=null){
                        filieres.add(filiere);

                        filiere.setRegleCalcul(regleCalcul);
                        filiereRepository.save(filiere);
                    }
                }

                regleCalcul.setFilieres(filieres);
            }


            regleCalculRepository.save(regleCalcul);

            return regleCalculMapper.fromRegleCalcul(regleCalcul);
        }

        return null;

    }

    /* Pour modifier une regle de calcul on va soit ajouter/suprimer une filiere || ajouter/supprimer une regle Annee
     || ajouter/supprimer une regle semestre.
     Alors il parait logique de separer update par ajout de l'update par suppression  donc je vais creer deux methode :
      - une pour ajouter une filiere, Regle Semestre ou regle Anne à une RegleCalcul
      - et l'autre pour la suppression d'une une filiere, Regle Semestre ou regle Anne dans une RegleCalcul*/

    @Override
    public RegleCalculResponseDTO updateRegleCalculParAjout(Long id, RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException {
        if(id!=null && regleCalculRequestDTO!=null) {
            RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(id);
            if (regleCalcul != null) {
                if (regleCalculRequestDTO.getIdFilieres() != null) {
                    for (Long f : regleCalculRequestDTO.getIdFilieres()) {
                        Filiere filiere = filiereRepository.findByIdAndSoftDeleteIsFalse(f);
                        if (filiere != null && regleCalculRepository.existsByIdAndFilieresId(regleCalcul.getId(), filiere.getId())!= true) {
                            regleCalcul.getFilieres().add(filiere);
                            filiere.setRegleCalcul(regleCalcul);
                            filiereRepository.save(filiere);
                        }
                    }

                }

                if(regleCalculRequestDTO.getIdReglesSemeste()!=null){
                    for(Long r : regleCalculRequestDTO.getIdReglesSemeste()) {
                        RegleSemestre regleSemestre = regleSemestreRepository.findByIdAndSoftDeleteIsFalse(r);
                        if (regleSemestre != null && regleCalculRepository.existsByIdAndReglesSemestreId(regleCalcul.getId(), regleSemestre.getId())!= true) {
                            regleCalcul.getReglesSemestre().add(regleSemestre);
                            regleSemestre.setRegleCalcul(regleCalcul);
                            regleSemestreRepository.save(regleSemestre);
                        }
                    }
                }

                if(regleCalculRequestDTO.getIdReglesAnnee()!=null){
                    for(Long r : regleCalculRequestDTO.getIdReglesAnnee()){
                        RegleAnnee regleAnnee = regleAnneeRepository.findByIdAndSoftDeleteIsFalse(r);
                        if(regleAnnee!=null && regleCalculRepository.existsByIdAndReglesAnneeId(regleCalcul.getId(),regleAnnee.getId())!=true) {
                            regleCalcul.getReglesAnnee().add(regleAnnee);
                            regleAnnee.setRegleCalcul(regleCalcul);
                            regleAnneeRepository.save(regleAnnee);
                        }
                    }
                }
                regleCalculRepository.save(regleCalcul);
                return regleCalculMapper.fromRegleCalcul(regleCalcul);
            } throw new RegleCalculNotFoundException(id);
        }
        return null;
    }


    @Override
    public RegleCalculResponseDTO updateRegleCalculParSuppression(Long id, RegleCalculRequestDTO regleCalculRequestDTO) throws RegleCalculNotFoundException, CannotProceedException {
        if(id!=null && regleCalculRequestDTO!=null) {
            RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(id);
            if (regleCalcul != null) {
                if (regleCalculRequestDTO.getIdFilieres() != null) {
                    for (Long f : regleCalculRequestDTO.getIdFilieres()) {
                        Filiere filiere = filiereRepository.findByIdAndSoftDeleteIsFalse(f);
                        if (filiere != null && regleCalculRepository.existsByIdAndFilieresId(regleCalcul.getId(), filiere.getId()) == true) {
                            regleCalcul.getFilieres().remove(filiere);
                            filiere.setRegleCalcul(null);
                            filiereRepository.save(filiere);
                        }
                    }
                    regleCalcul.setUpdatedOn(new Date());

                }

                if(regleCalculRequestDTO.getIdReglesSemeste()!=null){
                    for(Long r : regleCalculRequestDTO.getIdReglesSemeste()) {
                        RegleSemestre regleSemestre = regleSemestreRepository.findByIdAndSoftDeleteIsFalse(r);
                        if (regleSemestre != null && regleCalculRepository.existsByIdAndReglesSemestreId(regleCalcul.getId(), regleSemestre.getId())== true) {
                            regleCalcul.getReglesSemestre().remove(regleSemestre);
                            regleSemestre.setRegleCalcul(null);
                            regleSemestreRepository.save(regleSemestre);
                        }
                    }
                    regleCalcul.setUpdatedOn(new Date());
                }

                if(regleCalculRequestDTO.getIdReglesAnnee()!=null){
                    for(Long r : regleCalculRequestDTO.getIdReglesAnnee()){
                        RegleAnnee regleAnnee = regleAnneeRepository.findByIdAndSoftDeleteIsFalse(r);
                        if(regleAnnee!=null && regleCalculRepository.existsByIdAndReglesAnneeId(regleCalcul.getId(),regleAnnee.getId())==true) {
                            regleCalcul.getReglesAnnee().remove(regleAnnee);
                            regleAnnee.setRegleCalcul(null);
                            regleAnneeRepository.save(regleAnnee);
                        }
                    }
                    regleCalcul.setUpdatedOn(new Date());
                }
                regleCalculRepository.save(regleCalcul);
                return regleCalculMapper.fromRegleCalcul(regleCalcul);
            } throw new RegleCalculNotFoundException(id);
        }

        return null;
    }


    @Override
    public RegleCalculResponseDTO getRegleCalcul(Long id) throws RegleCalculNotFoundException {
            if(id==null) return null;

            RegleCalcul regleCalcul = regleCalculRepository.findById(id).orElseThrow(()->new RegleCalculNotFoundException(id));
            return regleCalculMapper.fromRegleCalcul(regleCalcul);
    }

    @Override
    public List<RegleCalculResponseDTO> getReglesCalcul() {
        List<RegleCalcul> regles = regleCalculRepository.findAll();

        List<RegleCalculResponseDTO> reglesResponse = new ArrayList<>();
        for (RegleCalcul r: regles) {
            RegleCalculResponseDTO responseDTO;
            responseDTO = regleCalculMapper.fromRegleCalcul(r);
            reglesResponse.add(responseDTO);
        }
        return reglesResponse;
    }

    @Override
    public void deleteRegleCalcul(Long id) throws RegleCalculNotFoundException {
        RegleCalcul regleCalcul = regleCalculRepository.findById(id).orElseThrow(()-> new RegleCalculNotFoundException(id));

        if(regleCalcul!= null){
            regleCalcul.setSoftDelete(true);
            regleCalcul.setUpdatedOn(new Date());
            regleCalculRepository.save(regleCalcul);

            List<Filiere> filieres = regleCalculRepository.findFilieresById(regleCalcul.getId());
            for (Filiere f: filieres) {
                f.setRegleCalcul(null);
                filiereRepository.save(f);
            }

            List<RegleAnnee> reglesAnnee = regleCalculRepository.findReglesAnneeById(regleCalcul.getId());
            for (RegleAnnee r: reglesAnnee) {
                r.setRegleCalcul(null);
                regleAnneeRepository.save(r);
            }

            List<RegleSemestre> reglesSemestre = regleCalculRepository.findReglesSemestreById(regleCalcul.getId());
            for (RegleSemestre r: reglesSemestre) {
                r.setRegleCalcul(null);
                regleSemestreRepository.save(r);
            }
        }
    }
}
