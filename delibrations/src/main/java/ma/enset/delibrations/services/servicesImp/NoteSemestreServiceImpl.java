package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.NoteSemestreMapper;
import ma.enset.delibrations.dtos.requests.NoteSemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteSemestreResponseDTO;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.entities.Semestre;
import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.entities.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.repositories.NoteSemestreRepository;
import ma.enset.delibrations.repositories.SemestreRepository;
import ma.enset.delibrations.services.NoteSemestreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class NoteSemestreServiceImpl implements NoteSemestreService {

    NoteSemestreRepository noteSemestreRepository;
    NoteSemestreMapper noteSemestreMapper;
    SemestreRepository semestreRepository;


    @Override
    public NoteSemestreResponseDTO addNoteSemestre(NoteSemestreRequestDTO noteSemestreRequestDTO) throws SemestreNotFoundException {
     if (noteSemestreRequestDTO!=null) {
         NoteSemestre noteSemestre = new NoteSemestre();
         BeanUtils.copyProperties(noteSemestreRequestDTO, noteSemestre);

         if (noteSemestreRequestDTO.getSemestreId()==null) throw new SemestreNotFoundException("required");
         if (noteSemestreRequestDTO.getSemestreId()!= null){
             Semestre semestre = semestreRepository.findById(noteSemestreRequestDTO.getSemestreId()).orElseThrow(() -> new SemestreNotFoundException(noteSemestreRequestDTO.getSemestreId()));
             noteSemestre.setSemestre(semestre);
         }
         noteSemestreRepository.save(noteSemestre);
            return noteSemestreMapper.fromEntitytoResponseDTO(noteSemestre);

     }
      return null;
    }

    @Override
    public NoteSemestreResponseDTO updateNoteSemestre(NoteSemestreRequestDTO noteSemestreRequestDTO) throws NoteSemestreNotFoundException, CannotProceedException, SemestreNotFoundException {
        Long id =noteSemestreRequestDTO.getId();
        if (id!=null && noteSemestreRequestDTO!=null) {
            NoteSemestre noteSemestre = noteSemestreRepository.findById(id).orElseThrow(() -> new NoteSemestreNotFoundException(id));
            if (noteSemestre != null) {
                if (noteSemestreRequestDTO.getSemestreId()==null) throw new SemestreNotFoundException("required");
                if (noteSemestreRequestDTO.getNoteSession1() != null)
                    noteSemestre.setNoteSession1(noteSemestreRequestDTO.getNoteSession1());
                if (noteSemestreRequestDTO.getNoteSession2() != null)
                    noteSemestre.setNoteSession2(noteSemestreRequestDTO.getNoteSession2());
                if (noteSemestreRequestDTO.getSemestreId()!= null){
                    Semestre semestre = semestreRepository.findById(noteSemestreRequestDTO.getSemestreId()).orElseThrow(() -> new SemestreNotFoundException(noteSemestreRequestDTO.getSemestreId()));
                    noteSemestre.setSemestre(semestre);
            }
                noteSemestreRepository.save(noteSemestre);
                NoteSemestreResponseDTO noteSemestreResponseDTO = noteSemestreMapper.fromEntitytoResponseDTO(noteSemestre);
                if (noteSemestre.getSemestre()!= null){
                    noteSemestreResponseDTO.setSemestre(noteSemestre.getSemestre().getId());
                }
                return noteSemestreResponseDTO;
            }

        }
        throw new CannotProceedException("Cannot update NoteSemestre" + id);
    }

    @Override
    public void deleteNoteSemestre(Long id) throws NoteSemestreNotFoundException {
        NoteSemestre noteSemestre = noteSemestreRepository.findById(id).orElseThrow(() -> new NoteSemestreNotFoundException(id));
        if (noteSemestre!=null) {
            noteSemestreRepository.delete(noteSemestre);
        }

    }

    @Override
    public List<NoteSemestreResponseDTO> getAllNotesSemestre() {
        List<NoteSemestre> noteSemestres = noteSemestreRepository.findAll();
        List<NoteSemestreResponseDTO> noteSemestreResponseDTOS = new ArrayList<>();
        for (NoteSemestre noteSemestre : noteSemestres) {
            NoteSemestreResponseDTO noteSemestreResponseDTO = noteSemestreMapper.fromEntitytoResponseDTO(noteSemestre);
            if (noteSemestre.getSemestre()!= null){
               noteSemestreResponseDTO.setSemestre(noteSemestre.getSemestre().getId());
            }
       noteSemestreResponseDTOS.add(noteSemestreResponseDTO);
        }
        return noteSemestreResponseDTOS;
    }

    @Override
    public NoteSemestreResponseDTO getNoteSemestre(Long id) throws NoteSemestreNotFoundException {
        NoteSemestre noteSemestre = noteSemestreRepository.findById(id).orElseThrow(() -> new NoteSemestreNotFoundException(id));
        if (noteSemestre == null) {
            throw new NoteSemestreNotFoundException(id);
        }
        NoteSemestreResponseDTO noteSemestreResponseDTO = noteSemestreMapper.fromEntitytoResponseDTO(noteSemestre);
        Semestre semestre = noteSemestre.getSemestre();
        if (semestre!= null){
            noteSemestreResponseDTO.setSemestre(semestre.getId());
        }
        return noteSemestreResponseDTO;

    }

    @Override
    public NoteSemestre getNoteSemestreById(Long id) throws NoteSemestreNotFoundException {
        NoteSemestre noteSemestre = noteSemestreRepository.findById(id).orElseThrow(() -> new NoteSemestreNotFoundException(id));
        if (noteSemestre == null) {
            throw new NoteSemestreNotFoundException(id);
        }
        return noteSemestre;
    }


}
