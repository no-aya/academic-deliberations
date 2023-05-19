package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.AnneeUnivMapper;
import ma.enset.delibrations.dtos.mappers.SemestreMapper;
import ma.enset.delibrations.dtos.requests.AnneeUnivRequestDTO;
import ma.enset.delibrations.dtos.responses.AnneeUnivResponseDTO;
import ma.enset.delibrations.entities.AnneeUniv;
import ma.enset.delibrations.entities.Semestre;
import ma.enset.delibrations.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.repositories.AnneeUnivRepository;
import ma.enset.delibrations.services.AnneeUnivService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.CannotProceedException;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class AnneeUnivServiceImpl implements AnneeUnivService {
    private AnneeUnivRepository anneeUnivRepository;
    private AnneeUnivMapper anneeUnivMapper;
    private SemestreMapper semestreMapper;
    private SemestreServiceImpl semestreService;

    @Override
    public AnneeUnivResponseDTO createAnneeUniv(AnneeUnivRequestDTO anneeUnivRequestDTO) throws CannotProceedException, NoteSemestreNotFoundException {
        if (anneeUnivRequestDTO != null){
            AnneeUniv anneeUniv = anneeUnivMapper.fromRequestDTOtoEntity(anneeUnivRequestDTO);
             if (anneeUnivRequestDTO.getSemestres()!=null){
                 for(Semestre semestre : anneeUniv.getSemestres()) {
                     semestre.setAnneeUniv(anneeUniv);
                     semestreService.createSemestre(semestreMapper.fromEntitytoRequestDTO(semestre));
                 }
             }
            anneeUnivRepository.save(anneeUniv);
            return anneeUnivMapper.fromEntityToResponseDTO(anneeUniv);
        }
        throw new CannotProceedException("AnneeUniv is null");
    }

    @Override
    public AnneeUnivResponseDTO updateAnneeUniv(Long id, AnneeUnivRequestDTO anneeUnivRequestDTO) throws AnneeUnivNotFoundException, SemestreNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException, NoteSemestreNotFoundException {
        if (id != null && anneeUnivRequestDTO != null){
            AnneeUniv anneeUniv = anneeUnivRepository.findById(id).orElseThrow(()->new AnneeUnivNotFoundException(id));
            if (anneeUniv == null) throw new AnneeUnivNotFoundException(id);
            if (anneeUnivRequestDTO.getCodeAnnee() != null) anneeUniv.setCodeAnnee(anneeUnivRequestDTO.getCodeAnnee());
            if (anneeUnivRequestDTO.getDateDebut() != null) anneeUniv.setDateDebut(anneeUnivRequestDTO.getDateDebut());
            if (anneeUnivRequestDTO.getDateFin() != null) anneeUniv.setDateFin(anneeUnivRequestDTO.getDateFin());
            if (anneeUnivRequestDTO.getSemestres() != null){
                Long[] semestresIds = anneeUnivRequestDTO.getSemestres();
                List<Semestre> semestres = new ArrayList<>();
                for (Long semestreId : semestresIds){
                    Semestre semestre = semestreService.getSemestre(semestreId);
                    if (semestre == null) throw new SemestreNotFoundException(semestre.getCode());
                    semestres.add(semestre);
                }
                anneeUniv.setSemestres(semestres);
                for (Semestre semestre : semestres){
                    semestre.setAnneeUniv(anneeUniv);
                    semestreService.updateSemestre(semestre.getCode(),semestreMapper.fromEntitytoRequestDTO(semestre));
                }
            }
            anneeUnivRepository.save(anneeUniv);
            return anneeUnivMapper.fromEntityToResponseDTO(anneeUniv);
        }
        throw new AnneeUnivNotFoundException(id);
    }

    @Override
    public AnneeUnivResponseDTO getAnneeUniv(Long id) throws AnneeUnivNotFoundException {
        if (id != null) {
            AnneeUniv anneeUniv = anneeUnivRepository.findById(id).orElseThrow(()->new AnneeUnivNotFoundException(id));
            return anneeUnivMapper.fromEntityToResponseDTO(anneeUniv);
        }else throw new AnneeUnivNotFoundException(id);
    }

    @Override
    public void deleteAnneeUniv(Long id) throws AnneeUnivNotFoundException {
        if (id != null){
            AnneeUniv anneeUniv = anneeUnivRepository.findById(id).orElseThrow(()->new AnneeUnivNotFoundException(id));
            if (anneeUniv == null) throw new AnneeUnivNotFoundException(id);
            anneeUnivRepository.delete(anneeUniv);
        }else throw new AnneeUnivNotFoundException(id);
    }

    @Override
    public List<AnneeUnivResponseDTO> getAnneeUnivs() {
        List<AnneeUniv> anneeUnivs = anneeUnivRepository.findAll();
        List<AnneeUnivResponseDTO> anneeUnivResponseDTOS = new ArrayList<>();
        anneeUnivs.forEach(anneeUniv -> anneeUnivResponseDTOS.add(anneeUnivMapper.fromEntityToResponseDTO(anneeUniv)));
        return anneeUnivResponseDTOS;
    }
}
