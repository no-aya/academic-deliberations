package ma.enset.delibrations.services.servicesImp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.NoteSemestreMapper;
import ma.enset.delibrations.dtos.mappers.SemestreMapper;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.entities.Semestre;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.repositories.SemestreRepository;
import ma.enset.delibrations.services.SemestreService;
import org.springframework.stereotype.Service;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional @AllArgsConstructor
public class SemestreServiceImp implements SemestreService {

    private SemestreRepository semestreRepository;
    private SemestreMapper semestreMapper;

    private NoteSemestreMapper noteSemestreMapper;
    private NoteSemestreServiceImpl noteSemestreService;

    @Override
    public SemestreResponseDTO createSemestre(SemestreRequestDTO semestreRequestDTO) throws CannotProceedException, NoteSemestreNotFoundException {
        if (semestreRequestDTO != null) {
            Semestre semestre = semestreMapper.fromRequestDTOtoEntity(semestreRequestDTO);
            //Get notesSemestresIds
            if (semestreRequestDTO.getNoteSemestres()!=null){
                Long[] notesSemestres = semestreRequestDTO.getNoteSemestres();
                List<NoteSemestre> noteSemestres = new ArrayList<>();
                for (Long noteSemestreId : notesSemestres) {
                    NoteSemestre noteSemestre = noteSemestreService.getNoteSemestreById(noteSemestreId);
                    noteSemestres.add(noteSemestre);
                }
                semestre.setNoteSemestres(noteSemestres);
            }
            semestreRepository.save(semestre);
            return semestreMapper.fromEntityToResponseDTO(semestre);
        }
        throw new CannotProceedException("Semestre is null");
    }

    @Override
    public SemestreResponseDTO updateSemestre(Long id, SemestreRequestDTO semestreResponseDTO) throws SemestreNotFoundException, NoteSemestreNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException {

        if(id!=null && semestreResponseDTO!=null) {
            Semestre semestre = semestreRepository.findById(id).orElse(null);
            if(semestre==null) throw  new SemestreNotFoundException(id);
            if(semestreResponseDTO.getCode()!=null) semestre.setCode(semestreResponseDTO.getCode());
            if (semestreResponseDTO.getLibelle()!=null) semestre.setLibelle(semestreResponseDTO.getLibelle());
            if (semestreResponseDTO.getNoteSemestres()!=null) {
                    Long[] notesSemestres = semestreResponseDTO.getNoteSemestres();
                    List<NoteSemestre> noteSemestres = new ArrayList<>();
                    for (Long noteSemestreId : notesSemestres) {
                        NoteSemestre noteSemestre = noteSemestreService.getNoteSemestreById(noteSemestreId);
                        noteSemestres.add(noteSemestre);
                    }
                    semestre.setNoteSemestres(noteSemestres);
                    for (NoteSemestre noteSemestre : noteSemestres) {
                        noteSemestre.setSemestre(semestre);
                        noteSemestreService.updateNoteSemestre(noteSemestreMapper.fromEntitytoRequestDTO(noteSemestre));
                    }

                }

                semestreRepository.save(semestre);
                return semestreMapper.fromEntityToResponseDTO(semestre);

        }
        throw new SemestreNotFoundException(id);
    }

    @Override
    public SemestreResponseDTO getSemestre(Long id) throws SemestreNotFoundException {
        if (id != null) {
            Semestre semestre = semestreRepository.findById(id).orElseThrow(() -> new SemestreNotFoundException(id));
            if (semestre != null) {
                return semestreMapper.fromEntityToResponseDTO(semestre);
            }
        }
        return null;
    }

    @Override
    public void deleteSemestre(Long id) throws SemestreNotFoundException {
        if (id != null) {
            semestreRepository.deleteById(id);
        }else {
            throw new SemestreNotFoundException(id);
        }
    }

    @Override
    public List<SemestreResponseDTO> getSemestres() {
        List<Semestre> semestres = semestreRepository.findAll();
        List<SemestreResponseDTO> semestreResponse = new ArrayList<>();
        for (Semestre semestre : semestres) {
            SemestreResponseDTO responseDTO = new SemestreResponseDTO();
            responseDTO = semestreMapper.fromEntityToResponseDTO(semestre);
            semestreResponse.add(responseDTO);
        }
        return semestreResponse;
    }

    @Override
    public SemestreResponseDTO getSemestreByCode(String code) {
        if (code != null) {
            Semestre semestre = semestreRepository.findByCode(code);
            if (semestre != null) {
                return semestreMapper.fromEntityToResponseDTO(semestre);
            }
        }
        return null;
    }


}
