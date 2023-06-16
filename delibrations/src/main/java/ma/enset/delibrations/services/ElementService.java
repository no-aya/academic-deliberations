package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.ElementRequestDTO;
import ma.enset.delibrations.dtos.responses.ElementResponseDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.exceptions.ElementNotFoundException;
import ma.enset.delibrations.entities.exceptions.ProfesseurNotFoundException;

import java.util.List;


public interface ElementService {
    ElementResponseDTO addElement(ElementRequestDTO elementRequestDTO);
    ElementResponseDTO updateElement(ElementRequestDTO elementRequestDTO) throws ElementNotFoundException, ProfesseurNotFoundException;
    void deleteElement(String code) throws ElementNotFoundException;
    List<ElementResponseDTO> getAllElements();
    ElementResponseDTO getElementByCode(String code);
    ElementResponseDTO getElement(String code) throws ElementNotFoundException;
    Element getElement(Long code) throws ElementNotFoundException;
    List<ElementResponseDTO> getElementWithModuleAndProf(Long idProf, Long idModule,String libelSemestre)  ;
}
