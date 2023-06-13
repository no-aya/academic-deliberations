package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.ElementMapper;
import ma.enset.delibrations.dtos.requests.ElementRequestDTO;
import ma.enset.delibrations.dtos.responses.ElementResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.exceptions.ElementNotFoundException;
import ma.enset.delibrations.entities.exceptions.ProfesseurNotFoundException;
import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.services.ElementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ElementServiceImpl implements ElementService {
    private ElementRepository elementRepository;
    private ElementMapper elementMapper;
    private ProfesseurRepository professeurRepository;
    private ModuleRepository moduleRepository;
    private SemestreRepository semestreRepository;
    private AnneeUnivRepository anneeUnivRepository;

    private String generateCode() {
        //TODO: Generate code based on the "Module", "Filiere" and "Semestre" attributes
        return "CODE"+System.currentTimeMillis();
    }

    @Override
    public ElementResponseDTO addElement(ElementRequestDTO elementRequestDTO) {
        Element element = elementRepository.findByCode(elementRequestDTO.getCode());
        if (element != null) throw new RuntimeException("Element with code "+elementRequestDTO.getCode()+" already exists");
        if (elementRequestDTO.getCode() == null) elementRequestDTO.setCode(generateCode());
        Element savedElement = elementRepository.save(elementMapper.fromRequestDTOtoEntity(elementRequestDTO));
        return elementMapper.fromEntitytoResponseDTO(savedElement);
    }

    @Override
    public ElementResponseDTO updateElement(ElementRequestDTO elementRequestDTO) throws ElementNotFoundException, ProfesseurNotFoundException {
        if (elementRequestDTO.getCode() == null) throw new ElementNotFoundException("Element code is required");
        else {
            Element element = elementRepository.findByCode(elementRequestDTO.getCode());
            if (element == null) throw new ElementNotFoundException(elementRequestDTO.getCode());
            if (elementRequestDTO.getTitre() != null) element.setTitre(elementRequestDTO.getTitre());
            if (elementRequestDTO.getPonderation() != null) element.setPonderation(elementRequestDTO.getPonderation());
            if (elementRequestDTO.getProfesseurId() != null) {
                Professeur professeur = professeurRepository.findById(elementRequestDTO.getProfesseurId()).orElseThrow(()->new ProfesseurNotFoundException(elementRequestDTO.getProfesseurId()));
                element.setProfesseur(professeur);
            }
            elementRepository.save(element);

            ElementResponseDTO elementResponseDTO=elementMapper.fromEntitytoResponseDTO(element);
            if (element.getProfesseur() != null){
                elementResponseDTO.setProfesseur(element.getProfesseur().getId());
            }

            return elementResponseDTO;
        }
    }

    @Override
    public void deleteElement(String code) throws ElementNotFoundException {
        Element element = elementRepository.findByCode(code);
        if (element == null) throw new ElementNotFoundException("Element with code "+code+" not found");
        elementRepository.delete(element);
    }

    @Override
    public List<ElementResponseDTO> getAllElements() {
        List<Element> elements = elementRepository.findAll();
        List<ElementResponseDTO> elementResponseDTOS = new ArrayList<>();
        for (Element element : elements) {
            ElementResponseDTO elementResponseDTO = elementMapper.fromEntitytoResponseDTO(element);
            if (element.getProfesseur() != null){
                elementResponseDTO.setProfesseur(element.getProfesseur().getId());
            }
            elementResponseDTOS.add(elementResponseDTO);
        }
        return elementResponseDTOS;
    }

    @Override
    public ElementResponseDTO getElementByCode(String code) {
        Element element = elementRepository.findByCode(code);
        if (element == null) throw new RuntimeException("Element with code "+code+" not found");
        ElementResponseDTO elementResponseDTO= elementMapper.fromEntitytoResponseDTO(element);
        Professeur professeur = element.getProfesseur();
        if (professeur!=null) elementResponseDTO.setProfesseur(professeur.getId());
        return elementResponseDTO;
    }

    @Override
    public ElementResponseDTO getElement(String code) throws ElementNotFoundException {
        Element element = elementRepository.findByCode(code);
        if (element == null) throw new ElementNotFoundException("Element with code "+code+" not found");
        ElementResponseDTO elementResponseDTO= elementMapper.fromEntitytoResponseDTO(element);
        Professeur professeur = element.getProfesseur();
        if (professeur!=null) elementResponseDTO.setProfesseur(professeur.getId());
        return elementResponseDTO;
    }

    @Override
    public Element getElement(Long id) throws ElementNotFoundException {
        return elementRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Element with id "+id+" not found"));
    }

    @Override
    public List<ElementResponseDTO> getElementWithModuleAndProf(Long idProf, Long idModule, String libelS) {
        if(idProf!=null && idProf!=null && libelS!=null ){
            Professeur prof = professeurRepository.findByIdAndSoftDeleteIsFalse(idProf);
            Module module= moduleRepository.findByIdAndSoftDeleteIsFalse(idModule);
            Semestre semestre = semestreRepository.findByLibelle(libelS);
            if(prof!=null && module!=null && semestre!=null){
                List<Element> elements = elementRepository.findByCleEtrangere(idProf);
                if (elements!=null){
                    List<Element> elementResponse=new ArrayList<>();
                    for (Element e: elements) {
                        if(e.getModule().getId()==idModule && e.getModule().getSemestre().getId()==semestre.getId()  ) elementResponse.add(e);
                    }

                    List<ElementResponseDTO> responses = elementResponse.stream()
                            .map(e -> elementMapper.fromEntitytoResponseDTO(e))
                            .collect(Collectors.toList());

                    Set<ElementResponseDTO> uniqueResponses = new HashSet<>(responses);
                    return new ArrayList<>(uniqueResponses);

                }
            }

        }
        return null;
    }
}
