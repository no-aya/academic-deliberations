package ma.enset.delibrations.services.servicesImp;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.InscriptionPedagogiqueMapper;
import ma.enset.delibrations.dtos.requests.InscriptionPedagogiqueRequestDTO;
import ma.enset.delibrations.dtos.responses.InscriptionpedagoqiqueResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.exceptions.*;

import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.services.InscriptionpedagogiqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class InscriptionpedagogiqueServiceImp implements InscriptionpedagogiqueService {

    private InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository;
    private InscriptionPedagogiqueMapper inscriptionPedagogiqueMapper;
    private ModuleRepository moduleRepository;
    private EtudiantRepository etudiantRepository;
    private NoteElementRepository noteElementRepository;
    private NoteSemestreRepository noteSemestreRepository;
    private  NoteModuleRepository noteModuleRepository;


    @Override
    public InscriptionpedagoqiqueResponseDTO createInscriptionpedagogique(InscriptionPedagogiqueRequestDTO inscriptionPedagogiqueRequestDTO) throws EtudiantNotFoundException, ModuleNotFoundException, NoteElementNotFoundException, NoteModuleNotFoundException {

        if(inscriptionPedagogiqueRequestDTO!=null){
            InscriptionPedagogique inscription = new InscriptionPedagogique();
            inscription.setCreatedAt(new Date());

           if(inscriptionPedagogiqueRequestDTO.getIdEtudiant()!=null){
               Etudiant etudiant = etudiantRepository.findByApogeeAndSoftDeleteIsFalse(inscriptionPedagogiqueRequestDTO.getIdEtudiant());
               if(etudiant!=null) inscription.setEtudiant(etudiant);
               else throw new EtudiantNotFoundException(inscriptionPedagogiqueRequestDTO.getIdEtudiant());
           }
            if(inscriptionPedagogiqueRequestDTO.getIdModule()!=null){
                Module module = moduleRepository.findByCodeAndSoftDeleteIsFalse(inscriptionPedagogiqueRequestDTO.getIdModule());
                if(module!=null) inscription.setModule(module);
                else throw new ModuleNotFoundException(inscriptionPedagogiqueRequestDTO.getIdModule());
            }
            if(inscriptionPedagogiqueRequestDTO.getIdNoteElement()!=null){
                NoteElement noteElement = noteElementRepository.findById(inscriptionPedagogiqueRequestDTO.getIdNoteElement()).orElse(null);
                if(noteElement!=null) inscription.setNoteElement(noteElement);
                else throw new NoteElementNotFoundException("Note Element with id : " + inscriptionPedagogiqueRequestDTO.getIdNoteElement() + "not found");

            }
            if(inscriptionPedagogiqueRequestDTO.getIdNoteSemestre()!=null){
                NoteSemestre noteSemestre = noteSemestreRepository.findById(inscriptionPedagogiqueRequestDTO.getIdNoteSemestre()).orElse(null);
                if(noteSemestre!=null) inscription.setNoteSemestre(noteSemestre);
                else throw new EtudiantNotFoundException(inscriptionPedagogiqueRequestDTO.getIdEtudiant());

            }

            if(inscriptionPedagogiqueRequestDTO.getIdNoteModule()!=null){
                NoteModule noteModule = noteModuleRepository.findById(inscriptionPedagogiqueRequestDTO.getIdNoteModule()).orElse(null);

                if(noteModule!=null) inscription.setNoteModule(noteModule);
                else throw new NoteModuleNotFoundException(inscriptionPedagogiqueRequestDTO.getIdNoteModule());

            }

            inscriptionPedagogiqueRepository.save(inscription);

            return inscriptionPedagogiqueMapper.fromInscriptionPedagogique(inscription);
        }

        return null;

    }

    @Override
    public InscriptionpedagoqiqueResponseDTO updateInscriptionpedagogique(Long id, InscriptionPedagogiqueRequestDTO inscriptionPedagogiqueRequestDTO) throws InscriptionPedagogiqueNotFoundException, EtudiantNotFoundException, ModuleNotFoundException, NoteElementNotFoundException, NoteSemestreNotFoundException, NoteModuleNotFoundException {
       if(id!=null && inscriptionPedagogiqueRequestDTO!=null){
           InscriptionPedagogique inscription = inscriptionPedagogiqueRepository.findById(id).orElseThrow(()->new InscriptionPedagogiqueNotFoundException(id) );

           if(inscription!=null){
               inscription.setUpdatedOn(new Date());

               if(inscriptionPedagogiqueRequestDTO.getIdEtudiant()!=null) {
                    Etudiant etudiant = etudiantRepository.findByApogeeAndSoftDeleteIsFalse(inscriptionPedagogiqueRequestDTO.getIdEtudiant());
                    if(etudiant!=null) inscription.setEtudiant(etudiant);
                    else throw new EtudiantNotFoundException(inscriptionPedagogiqueRequestDTO.getIdEtudiant());
               }

               if(inscriptionPedagogiqueRequestDTO.getIdModule()!=null) {
                   Module module = moduleRepository.findByCodeAndSoftDeleteIsFalse(inscriptionPedagogiqueRequestDTO.getIdModule());
                   if(module!=null) inscription.setModule(module);
                   else throw new ModuleNotFoundException(inscriptionPedagogiqueRequestDTO.getIdModule());
               }

               if(inscriptionPedagogiqueRequestDTO.getIdNoteElement()!=null) {
                   NoteElement noteElement = noteElementRepository.findById(inscriptionPedagogiqueRequestDTO.getIdNoteElement()).orElse(null);

                   if(noteElement!=null) inscription.setNoteElement(noteElement);
                   else throw new  NoteElementNotFoundException("Note Element with id : " + inscriptionPedagogiqueRequestDTO.getIdNoteElement() + "not found");
               }

               if(inscriptionPedagogiqueRequestDTO.getIdNoteSemestre()!=null) {
                   NoteSemestre noteSemestre = noteSemestreRepository.findById(inscriptionPedagogiqueRequestDTO.getIdNoteSemestre()).orElse(null);

                   if(noteSemestre!=null) inscription.setNoteSemestre(noteSemestre);
                   else throw new  NoteSemestreNotFoundException(inscriptionPedagogiqueRequestDTO.getIdNoteSemestre());
               }

               if(inscriptionPedagogiqueRequestDTO.getIdNoteModule()!=null) {
                   NoteModule noteModule = noteModuleRepository.findById(inscriptionPedagogiqueRequestDTO.getIdNoteModule()).orElse(null);

                   if(noteModule!=null) inscription.setNoteModule(noteModule);
                   else throw new  NoteModuleNotFoundException(inscriptionPedagogiqueRequestDTO.getIdNoteModule());
               }

               inscriptionPedagogiqueRepository.save(inscription);
               return inscriptionPedagogiqueMapper.fromInscriptionPedagogique(inscription);

           }
           else try {
               throw new CannotProceedException("Cannot update  Inscription Pedagogique "+id);
           } catch (CannotProceedException e) {
               throw new RuntimeException(e);
           }
       }
        return null;
    }

    @Override
    public InscriptionpedagoqiqueResponseDTO getInscriptionpedagogique(Long id) throws InscriptionPedagogiqueNotFoundException {
        if(id==null)  return null;
            InscriptionPedagogique inscription = inscriptionPedagogiqueRepository.findByIdAndSoftDeleteIsFalse(id);
            if(inscription!=null) return inscriptionPedagogiqueMapper.fromInscriptionPedagogique(inscription);
            else throw new InscriptionPedagogiqueNotFoundException(id);

    }

    @Override
    public List<InscriptionpedagoqiqueResponseDTO> getInscriptionspedagogique() {
        List<InscriptionPedagogique> inscriptions = inscriptionPedagogiqueRepository.findBySoftDeleteIsFalse();

        List<InscriptionpedagoqiqueResponseDTO> InscriptionsResponseDTO = new ArrayList<>();
        for (InscriptionPedagogique i: inscriptions) {
            InscriptionpedagoqiqueResponseDTO responseDTO;
            responseDTO = inscriptionPedagogiqueMapper.fromInscriptionPedagogique(i);
            InscriptionsResponseDTO.add(responseDTO);
        }
        return InscriptionsResponseDTO;
    }

    @Override
    public void deletegetInscriptionpedagogique(Long id) throws InscriptionPedagogiqueNotFoundException {
        InscriptionPedagogique inscriptionPedagogique = inscriptionPedagogiqueRepository.findById(id).orElseThrow(()-> new InscriptionPedagogiqueNotFoundException(id));
        inscriptionPedagogique.setUpdatedOn(new Date());
        if(inscriptionPedagogique!= null) inscriptionPedagogique.setSoftDelete(true);
    }
}
