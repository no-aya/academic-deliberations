package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.mappers.NoteElementMapper;
import ma.enset.delibrations.dtos.requests.NoteElementRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.dtos.responses.NoteElementResponseDTO;

import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.repositories.*;

import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.NoteElement;
import ma.enset.delibrations.entities.exceptions.ElementNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteElementNotFoundException;
import ma.enset.delibrations.repositories.ElementRepository;
import ma.enset.delibrations.repositories.NoteElementRepository;

import ma.enset.delibrations.services.NoteElementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class NoteElementServiceImpl implements NoteElementService {
    NoteElementRepository noteElementRepository;
    NoteElementMapper noteElementMapper;
    ElementRepository elementRepository;
    ModuleRepository moduleRepository;
    EtudiantRepository etudiantRepository;
    InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
    NoteModuleRepository noteModuleRepository;

    @Override
    public NoteElementResponseDTO createNoteElement(NoteElementRequestDTO noteElementRequestDTO) throws ElementNotFoundException {
        NoteElement noteElement = noteElementMapper.fromRequestDTOtoEntity(noteElementRequestDTO);
        //Check if the element exists

        Element element = elementRepository.findById(noteElementRequestDTO.getIdElement()).orElseThrow(()-> new ElementNotFoundException(noteElementRequestDTO.getIdElement()+" not found"));
        //TODO: Check InscriptionPédagogique
        noteElement.setElement(element);
        noteElement.setCreatedAt(new Date());
        NoteElement savedNoteElement = noteElementRepository.save(noteElement);
        return noteElementMapper.fromEntitytoResponseDTO(savedNoteElement);
    }

    @Override
    public NoteElementResponseDTO updateNoteElement(NoteElementRequestDTO noteElementRequestDTO) throws NoteElementNotFoundException, ElementNotFoundException {

        if (noteElementRequestDTO.getId() != null) {
            NoteElement noteElement = noteElementRepository.findById(noteElementRequestDTO.getId() ).orElseThrow(()-> new NoteElementNotFoundException("Note Element "+noteElementRequestDTO.getId()+" not found"));

            if (noteElementRequestDTO.getNoteSession1() != null) noteElement.setNoteSession1(noteElementRequestDTO.getNoteSession1());
            if (noteElementRequestDTO.getNoteSession2() != null) noteElement.setNoteSession2(noteElementRequestDTO.getNoteSession2());
            if (noteElementRequestDTO.getIdElement() != null) {
                Element element = elementRepository.findById(noteElementRequestDTO.getIdElement()).orElseThrow(()-> new ElementNotFoundException(noteElementRequestDTO.getIdElement()+" not found"));
                noteElement.setElement(element);
            }

            //TODO: Check InscriptionPédagogique
            noteElement.setUpdatedOn(new Date());
            noteElementRepository.save(noteElement);

            //Modifier NoteElement
            InscriptionPedagogique inscriptionPedagogique = inscriptionPedagogiqueRepository.findByCleEtrangereNoteElement(noteElement.getId());

            NoteModule noteModule = noteModuleRepository.findById(inscriptionPedagogique.getNoteModule().getId()).orElse(null);
            if(noteModule!=null){
                List<InscriptionPedagogique> inscrips = inscriptionPedagogiqueRepository.findByCleEtrangereModule(noteModule.getModule().getId());
                for (InscriptionPedagogique i: inscrips) {
                    if(i.getNoteElement().getId()!=noteElement.getId() && i.getEtudiant().getId()==inscriptionPedagogique.getEtudiant().getId()){
                        NoteElement noteElement2 = i.getNoteElement();

                        if(noteElement.getNoteSession1()!=null && noteElement2.getNoteSession1()!=null && noteElement.getNoteSession2()==null && noteElement2.getNoteSession2()==null){
                            Double nModule = noteElement.getNoteSession1()*noteElement.getElement().getCoeficient()+noteElement2.getNoteSession1()*noteElement2.getElement().getCoeficient();
                            if (nModule>=12 && noteElement.getNoteSession1()>=6 && noteElement2.getNoteSession1()>=6 ){
                                noteModule.setNoteSession1(nModule);
                                noteModule.setNoteSession2(nModule);
                                noteModule.setStatut("VAL");
                            }else{
                                noteModule.setNoteSession1(nModule);
                                noteModule.setStatut("RAT");
                            }
                        }else if(noteElement.getNoteSession1()!=null && noteElement2.getNoteSession1()!=null && noteElement.getNoteSession2()!=null && noteElement2.getNoteSession2()!=null){
                            Double nModule=null,note=noteElement.getNoteSession2()*noteElement.getElement().getCoeficient()+noteElement2.getNoteSession2()*noteElement2.getElement().getCoeficient();;
                            if(note>=12 && noteElement.getNoteSession2()>=6 && noteElement2.getNoteSession2()>=6){
                                noteModule.setStatut("V Aprés RAT");
                                if(note>=noteModule.getNoteSession1()){
                                    if(note>12) nModule=12.0;
                                    else nModule=note;
                                }else if(noteModule.getNoteSession1()>note){
                                    if(noteModule.getNoteSession1()>12) nModule=12.0;
                                    else nModule=noteModule.getNoteSession1();
                                }
                                noteModule.setNoteSession2(nModule);
                            }else{
                                noteModule.setNoteSession2(note);
                                noteModule.setStatut("NON VAL");
                            }

                        }else if(noteElement.getNoteSession1()!=null && noteElement2.getNoteSession1()!=null && (noteElement.getNoteSession2()==null || noteElement2.getNoteSession2()==null)){
                           if(noteElement.getNoteSession2()==null){
                               noteModule.setNoteSession2(noteElement.getNoteSession1()*noteElement.getElement().getCoeficient()+noteElement2.getNoteSession2()*noteElement2.getElement().getCoeficient());
                               if(noteElement2.getNoteSession2()>=12){
                                   noteModule.setStatut("V Aprés RAT");
                               }else{
                                   noteModule.setStatut("Non V");
                               }
                           }else{
                               noteModule.setNoteSession2(noteElement.getNoteSession2()*noteElement.getElement().getCoeficient()+noteElement2.getNoteSession1()*noteElement2.getElement().getCoeficient());
                               if(noteElement.getNoteSession2()>=12){
                                   noteModule.setStatut("V Aprés RAT");
                               }else{
                                   noteModule.setStatut("Non V");
                               }
                           }
                        }
                    }
                }

            }


            return noteElementMapper.fromEntitytoResponseDTO(noteElement);
        }
        throw new NoteElementNotFoundException("Cannot be Null");
    }

    @Override
    public NoteElementResponseDTO getNoteElement(Long id) throws NoteElementNotFoundException {
        NoteElement noteElement = noteElementRepository.findById(id).orElseThrow(()-> new NoteElementNotFoundException("Note Element "+id+" not found"));
        return noteElementMapper.fromEntitytoResponseDTO(noteElement);
    }

    @Override
    public void deleteNoteElement(Long id) throws NoteElementNotFoundException {
        NoteElement noteElement = noteElementRepository.findById(id).orElseThrow(()-> new NoteElementNotFoundException("Note Element "+id+" not found"));
        noteElementRepository.delete(noteElement);
    }

    @Override
    public List<NoteElementResponseDTO> getNoteElements() {
        List<NoteElement> noteElements = noteElementRepository.findAll();
        List<NoteElementResponseDTO> noteElementResponseDTOS = new ArrayList<>();
        for (NoteElement noteElement: noteElements) {
            NoteElementResponseDTO noteElementResponseDTO = noteElementMapper.fromEntitytoResponseDTO(noteElement);
            noteElementResponseDTO.setIdElement(noteElement.getElement().getId());
            noteElementResponseDTOS.add(noteElementResponseDTO);
        }
        return noteElementResponseDTOS;
    }

    @Override
    public NoteElementResponseDTO getNoteModuleByInscriptionPedagogique(Long idEtu, Long idModule, Long idElement) {
        if (idModule!=null && idEtu!=null && idElement!=null){
            Etudiant etudiant= etudiantRepository.findByIdAndSoftDeleteIsFalse(idEtu);
            Module module = moduleRepository.findByIdAndSoftDeleteIsFalse(idModule);
            Element element = elementRepository.findById(idElement).orElse(null);
            if(module!=null  && etudiant!=null && element!=null){
                List<InscriptionPedagogique> inscriptions= inscriptionPedagogiqueRepository.findByCleEtrangereModule(module.getId());
                if(inscriptions!=null){
                    for (InscriptionPedagogique i: inscriptions) {
                        if(i.getEtudiant().getId()==idEtu && i.getNoteElement().getElement().getId()==idElement){
                            NoteElement noteElement=noteElementRepository.findById(i.getNoteElement().getId()).orElse(null);
                            return noteElementMapper.fromEntitytoResponseDTO(noteElement);
                        }
                    }
                }
            }

        }
        return null;
    }
}