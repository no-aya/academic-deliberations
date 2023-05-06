package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.ElementMapper;
import ma.enset.delibrations.dtos.requests.ElementRequestDTO;
import ma.enset.delibrations.dtos.responses.ElementResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.repositories.ElementRepository;
import ma.enset.delibrations.services.ElementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ElementServiceImpl implements ElementService {
    ElementRepository elementRepository;
    ElementMapper elementMapper;

    private String generateCode() {
        //TODO: Generate code based on the "Module", "Filiere" and "Semestre" attributes
        return "CODE"+System.currentTimeMillis();
    }

    @Override
    public ElementResponseDTO addElement(ElementRequestDTO elementRequestDTO) {
        if (elementRequestDTO.getCode() == null) elementRequestDTO.setCode(generateCode());
        else {
            Element element = elementRepository.findByCode(elementRequestDTO.getCode());
            if (element != null) throw new RuntimeException("Element with code "+elementRequestDTO.getCode()+" already exists");
        }
        Element savedElement = elementRepository.save(elementMapper.fromRequestDTOtoEntity(elementRequestDTO));
        return elementMapper.fromEntitytoResponseDTO(savedElement);
    }



    @Override
    public ElementResponseDTO updateElement(ElementRequestDTO elementRequestDTO) throws ElementNotFoundException {
        if (elementRequestDTO.getCode() == null) throw new ElementNotFoundException("Element code is required");
        else {
            Element element = elementRepository.findByCode(elementRequestDTO.getCode());
            if (element == null) throw new ElementNotFoundException("Element with code "+elementRequestDTO.getCode()+" not found");
            Element savedElement = elementRepository.save(elementMapper.fromRequestDTOtoEntity(elementRequestDTO));

            return elementMapper.fromEntitytoResponseDTO(savedElement);
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
            elementResponseDTOS.add(elementMapper.fromEntitytoResponseDTO(element));
        }
        return elementResponseDTOS;
    }

    @Override
    public ElementResponseDTO getElementByCode(String code) {
        Element element = elementRepository.findByCode(code);
        if (element == null) throw new RuntimeException("Element with code "+code+" not found");
        return elementMapper.fromEntitytoResponseDTO(element);
    }
}
