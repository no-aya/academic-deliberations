package ma.enset.delibrations.services.servicesImp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.NoteSemestreMapper;
import ma.enset.delibrations.dtos.mappers.SemestreMapper;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.entities.AnneeUniv;
import ma.enset.delibrations.entities.NoteSemestre;
import ma.enset.delibrations.entities.Semestre;

import ma.enset.delibrations.entities.exceptions.*;
import ma.enset.delibrations.repositories.AnneeUnivRepository;
import ma.enset.delibrations.repositories.SemestreRepository;
import ma.enset.delibrations.services.SemestreService;
import org.springframework.stereotype.Service;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class SemestreServiceImpl implements SemestreService {

    private SemestreRepository semestreRepository;
    private SemestreMapper semestreMapper;

    private NoteSemestreMapper noteSemestreMapper;
    private NoteSemestreServiceImpl noteSemestreService;

    private AnneeUnivRepository anneeUnivRepository;

    private String generateCode() {
        //TODO: Generate code based on the "Module", "Filiere" and "Semestre" attributes
        return "CODE"+System.currentTimeMillis();
    }

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
                semestre.setCode(generateCode());
                semestre.setNoteSemestres(noteSemestres);
            }
            // Create session
            /*Session session = new Session();
            semestreRequestDTO.setSessionId(session.getId());*/
            semestreRepository.save(semestre);
            return semestreMapper.fromEntityToResponseDTO(semestre);
        }
        throw new CannotProceedException("Semestre is null");
    }

    @Override
    public SemestreResponseDTO updateSemestre(String id, SemestreRequestDTO semestreRequestDTO) throws SemestreNotFoundException, NoteSemestreNotFoundException, ma.enset.delibrations.entities.exceptions.CannotProceedException, AnneeUnivNotFoundException {

        if(id!=null && semestreRequestDTO!=null) {
            Semestre semestre = semestreRepository.findByCode(id);
            if(semestre==null) throw  new SemestreNotFoundException(id);
            //semestre.setSessionStatus(semestreRequestDTO.isSessionStatus());
            if(semestreRequestDTO.getCode()!=null) semestre.setCode(semestreRequestDTO.getCode());
            if (semestreRequestDTO.getLibelle()!=null) semestre.setLibelle(semestreRequestDTO.getLibelle());

            if (semestreRequestDTO.getNoteSemestres()!=null) {
                    Long[] notesSemestres = semestreRequestDTO.getNoteSemestres();
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
            if (semestreRequestDTO.getAnneeUnivId()!=null) {
                AnneeUniv anneeUniv = anneeUnivRepository.findById(semestreRequestDTO.getAnneeUnivId()).orElseThrow(()-> new AnneeUnivNotFoundException(semestreRequestDTO.getAnneeUnivId()));
                semestre.setAnneeUniv(anneeUniv);
            }

                semestreRepository.save(semestre);
                SemestreResponseDTO semestreResponseDTO = semestreMapper.fromEntityToResponseDTO(semestre);
                if (semestre.getAnneeUniv()!=null)
                    semestreResponseDTO.setAnneeUnivId(semestre.getAnneeUniv().getId());
                return semestreResponseDTO;


        }
        throw new SemestreNotFoundException(id);
    }

    @Override
    public SemestreResponseDTO getSemestre(String id) throws SemestreNotFoundException {
        if (id != null) {
            Semestre semestre = semestreRepository.findByCode(id);
            if (semestre != null) {
                SemestreResponseDTO semestreResponseDTO = semestreMapper.fromEntityToResponseDTO(semestre);
                AnneeUniv anneeUniv = semestre.getAnneeUniv();
                if (anneeUniv != null) {
                    semestreResponseDTO.setAnneeUnivId(anneeUniv.getId());
                }
                return semestreResponseDTO;
            }
        }
        throw new SemestreNotFoundException(id);
    }

    @Override
    public void deleteSemestre(String id) throws SemestreNotFoundException {
        if (id != null) {
            Semestre semestre = semestreRepository.findByCode(id);
            if (semestre != null) {
                semestreRepository.delete(semestre);
            } else {
                throw new SemestreNotFoundException(id);
            }
        }
    }

    @Override
    public List<SemestreResponseDTO> getSemestres() {
        List<Semestre> semestres = semestreRepository.findAll();
        List<SemestreResponseDTO> semestreResponse = new ArrayList<>();
        for (Semestre semestre : semestres) {
            SemestreResponseDTO responseDTO =  semestreMapper.fromEntityToResponseDTO(semestre);
            if (semestre.getAnneeUniv()!=null)
                responseDTO.setAnneeUnivId(semestre.getAnneeUniv().getId());
            semestreResponse.add(responseDTO);
        }
        return semestreResponse;
    }

    @Override
    public SemestreResponseDTO getSemestreByCode(String code) {
        if (code != null) {
            Semestre semestre = semestreRepository.findByCode(code);
            if (semestre != null) {
                SemestreResponseDTO semestreResponseDTO = semestreMapper.fromEntityToResponseDTO(semestre);
                AnneeUniv anneeUniv = semestre.getAnneeUniv();
                if (anneeUniv != null) {
                    semestreResponseDTO.setAnneeUnivId(anneeUniv.getId());
                }
                return semestreResponseDTO;
            }
        }
        return null;
    }

    @Override
    public Semestre getSemestre(Long id) throws SemestreNotFoundException {
        if (id != null) {
            Semestre semestre = semestreRepository.findById(id).orElse(null);
            if (semestre != null) {
                return semestre;
            }
        }
        throw new SemestreNotFoundException(id);
    }


}
