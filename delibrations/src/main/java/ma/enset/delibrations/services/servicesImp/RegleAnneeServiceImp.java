package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.RegleAnneeMapper;
import ma.enset.delibrations.dtos.requests.RegleAnneeRequestDTO;
import ma.enset.delibrations.dtos.responses.RegleAnneeResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.RegleAnneeNotFoundException;
import ma.enset.delibrations.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.repositories.RegleAnneeRepository;
import ma.enset.delibrations.repositories.RegleCalculRepository;
import ma.enset.delibrations.services.RegleAnneeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class RegleAnneeServiceImp implements RegleAnneeService {

    private RegleAnneeRepository regleAnneeRepository;
    private RegleAnneeMapper regleAnneeMapper;
    private RegleCalculRepository regleCalculRepository;


    @Override
    public RegleAnneeResponseDTO createRegleAnnee(RegleAnneeRequestDTO regleAnneeRequestDTO) throws RegleCalculNotFoundException {
        if(regleAnneeRequestDTO!=null) {
            RegleAnnee regleAnnee = new RegleAnnee();
            regleAnnee.setCreatedAt(new Date());

            BeanUtils.copyProperties(regleAnneeRequestDTO, regleAnnee);
            if(regleAnneeRequestDTO.getIdRegleCalcul()!=null){
                RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(regleAnneeRequestDTO.getIdRegleCalcul());

                if(regleCalcul!=null) regleAnnee.setRegleCalcul(regleCalcul);
                else throw new RegleCalculNotFoundException(regleAnneeRequestDTO.getIdRegleCalcul());
            }

            regleAnneeRepository.save(regleAnnee);
            return regleAnneeMapper.fromRegleAnnee(regleAnnee);
        }
        return null;

    }

    @Override
    public RegleAnneeResponseDTO updateRegleAnnee(Long id, RegleAnneeRequestDTO regleAnneeRequestDTO) throws RegleAnneeNotFoundException, CannotProceedException {
        if(id!=null && regleAnneeRequestDTO!=null) {
            RegleAnnee regleAnnee = regleAnneeRepository.findByIdAndSoftDeleteIsFalse(id);
            if(regleAnnee!=null){
                regleAnnee.setUpdatedOn(new Date());
                if(regleAnneeRequestDTO.getNoteEliminatoire()!=null) regleAnnee.setNoteEliminatoire(regleAnneeRequestDTO.getNoteEliminatoire());
                if(regleAnneeRequestDTO.getNombreModuleDerogation()!=null) regleAnnee.setNombreModuleDerogation(regleAnneeRequestDTO.getNombreModuleDerogation());
                if(regleAnneeRequestDTO.getNoteValidation()!=null) regleAnnee.setNoteValidation(regleAnneeRequestDTO.getNoteValidation());
                if(regleAnneeRequestDTO.getIdRegleCalcul()!=null){
                    RegleCalcul regleCalcul = regleCalculRepository.findByIdAndSoftDeleteIsFalse(regleAnneeRequestDTO.getIdRegleCalcul());
                    if(regleCalcul!=null) regleAnnee.setRegleCalcul(regleCalcul);
                }
                regleAnneeRepository.save(regleAnnee);
                return regleAnneeMapper.fromRegleAnnee(regleAnnee);
            }
            else throw new RegleAnneeNotFoundException(id);

        }
        throw new CannotProceedException("Cannot update Regle Annee "+id);

    }

    @Override
    public RegleAnneeResponseDTO getRegleAnnee(Long id) throws RegleAnneeNotFoundException {
        if(id==null) return null;
        RegleAnnee regleAnnee = regleAnneeRepository.findByIdAndSoftDeleteIsFalse(id);
        if(regleAnnee!=null) return regleAnneeMapper.fromRegleAnnee(regleAnnee);
        else throw new RegleAnneeNotFoundException(id);
    }

    @Override
    public List<RegleAnneeResponseDTO> getReglesAnnee() {
        List<RegleAnnee> regles = regleAnneeRepository.findAll();

        List<RegleAnneeResponseDTO> reglesResponse = new ArrayList<>();
        for (RegleAnnee r: regles) {
            RegleAnneeResponseDTO responseDTO;
            responseDTO = regleAnneeMapper.fromRegleAnnee(r);
            reglesResponse.add(responseDTO);
        }
        return reglesResponse;
    }

    @Override
    public void deleteRegleAnnee(Long id) {
        if(id!=null){
            RegleAnnee regleAnnee = regleAnneeRepository.findByIdAndSoftDeleteIsFalse(id);
            if(regleAnnee!=null) regleAnnee.setSoftDelete(true);
        }
    }

}
