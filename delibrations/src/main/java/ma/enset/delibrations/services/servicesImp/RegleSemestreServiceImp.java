package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.RegleSemestreMapper;
import ma.enset.delibrations.dtos.requests.RegleSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleSemestreResponseDTO;
import ma.enset.delibrations.entities.RegleCalcul;
import ma.enset.delibrations.entities.RegleSemestre;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.entities.exceptions.RegleSemestreNotFoundException;
import ma.enset.delibrations.repositories.RegleCalculRepository;
import ma.enset.delibrations.repositories.RegleSemestreRepository;
import ma.enset.delibrations.services.RegleSemestreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class RegleSemestreServiceImp implements RegleSemestreService {

    private RegleSemestreRepository regleSemestreRepository;
    private RegleSemestreMapper regleSemestreMapper;
    private RegleCalculRepository regleCalculRepository;


    @Override
    public RegleSemestreResponseDTO createRegleSemestre(RegleSemestreRequestDTO regleSemestreRequestDTO) throws RegleCalculNotFoundException {
        if(regleSemestreRequestDTO!=null) {
            RegleSemestre regleSemestre = new RegleSemestre();
            regleSemestre.setCreatedAt(new Date());

            BeanUtils.copyProperties(regleSemestreRequestDTO, regleSemestre);
            if(regleSemestreRequestDTO.getIdRegleCalcul()!=null){
                RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(regleSemestreRequestDTO.getIdRegleCalcul());

                if(regleCalcul!=null) regleSemestre.setRegleCalcul(regleCalcul);
                else throw new RegleCalculNotFoundException(regleSemestreRequestDTO.getIdRegleCalcul());
            }


            regleSemestreRepository.save(regleSemestre);
            return regleSemestreMapper.fromRegleSemestre(regleSemestre);
        }
        return null;

    }

    @Override
    public RegleSemestreResponseDTO updateRegleSemestre(Long id, RegleSemestreRequestDTO regleSemestreRequestDTO) throws RegleSemestreNotFoundException, CannotProceedException {
        if(id!=null && regleSemestreRequestDTO!=null) {
            RegleSemestre regleSemestre = regleSemestreRepository.findById(id).orElseThrow(()->new RegleSemestreNotFoundException(id));
            if(regleSemestre!=null){
                regleSemestre.setUpdatedOn(new Date());
                if(regleSemestreRequestDTO.getNoteValidation()!=null) regleSemestre.setNoteValidation(regleSemestreRequestDTO.getNoteValidation());
                if(regleSemestreRequestDTO.getNoteCompensation()!=null) regleSemestre.setNoteCompensation(regleSemestreRequestDTO.getNoteCompensation());
                if(regleSemestreRequestDTO.getNoteEliminatoire()!=null) regleSemestre.setNoteEliminatoire(regleSemestreRequestDTO.getNoteEliminatoire());
                if(regleSemestreRequestDTO.getIdRegleCalcul()!=null){
                    RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(regleSemestreRequestDTO.getIdRegleCalcul());
                    regleSemestre.setRegleCalcul(regleCalcul);
                }
                regleSemestreRepository.save(regleSemestre);
                return regleSemestreMapper.fromRegleSemestre(regleSemestre);
            }
            else throw new RegleSemestreNotFoundException(id);

        }
        throw new CannotProceedException("Cannot update Regle Semestre "+id);

    }

    @Override
    public RegleSemestreResponseDTO getRegleSemestre(Long id) throws RegleSemestreNotFoundException {
        if(id==null) return null;
        RegleSemestre regleSemestre = regleSemestreRepository.findByIdAndSoftDeleteIsFalse(id);
        if(regleSemestre!=null) return regleSemestreMapper.fromRegleSemestre(regleSemestre);
        else throw new RegleSemestreNotFoundException(id);
    }

    @Override
    public List<RegleSemestreResponseDTO> getReglesSemestre() {
        List<RegleSemestre> regles = regleSemestreRepository.findAll();

        List<RegleSemestreResponseDTO> reglesResponse = new ArrayList<>();
        for (RegleSemestre r: regles) {
            RegleSemestreResponseDTO responseDTO;
            responseDTO = regleSemestreMapper.fromRegleSemestre(r);
            reglesResponse.add(responseDTO);
        }
        return reglesResponse;
    }

    @Override
    public void deleteRegleSemestre(Long id) {
        if(id!=null){
            RegleSemestre regleSemestre = regleSemestreRepository.findByIdAndSoftDeleteIsFalse(id);
            if(regleSemestre!=null) regleSemestre.setSoftDelete(true);
        }
    }
}
