package ma.enset.delibrations.services.servicesImp;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.NoteModuleMapper;
import ma.enset.delibrations.dtos.requests.NoteModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteModuleResponseDTO;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.NoteModule;
import ma.enset.delibrations.exceptions.NoteModuleNotFoundException;
import ma.enset.delibrations.repositories.ModuleRepository;
import ma.enset.delibrations.repositories.NoteModuleRepository;
import ma.enset.delibrations.services.NoteModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class NoteModuleServiceImpl implements NoteModuleService {

    private NoteModuleRepository noteModuleRepository;
    private NoteModuleMapper noteModuleMapper;
    private ModuleRepository moduleRepository;

    @Override
    public NoteModuleResponseDTO createNoteModule(NoteModuleRequestDTO noteModuleRequestDTO) throws NoteModuleNotFoundException {
        if (noteModuleRequestDTO != null) {
            NoteModule noteModule = new NoteModule();
            BeanUtils.copyProperties(noteModuleRequestDTO, noteModule);
            noteModule.setCreatedAt(new Date());
            if (noteModuleRequestDTO.getIdModule() != null) {
                Module module = moduleRepository.findById(noteModuleRequestDTO.getIdModule()).orElseThrow(() -> new NoteModuleNotFoundException(noteModuleRequestDTO.getId()));
                noteModule.setModule(module);
            }
            noteModuleRepository.save(noteModule);
            return noteModuleMapper.fromEntitytoResponseDTO(noteModule);

        }
        return null;
    }


    @Override
    public NoteModuleResponseDTO updateNoteModule(NoteModuleRequestDTO noteModuleRequestDTO) throws NoteModuleNotFoundException {
        if (noteModuleRequestDTO.getIdModule() !=null) {
            NoteModule noteModule = noteModuleRepository.findById(noteModuleRequestDTO.getIdModule()).orElseThrow(() -> new NoteModuleNotFoundException(noteModuleRequestDTO.getId()));

                if (noteModuleRequestDTO.getNoteSession1() != 0.0)
                    noteModule.setNoteSession1(noteModuleRequestDTO.getNoteSession1());
                if (noteModuleRequestDTO.getNoteSession2() != 0.0)
                    noteModule.setNoteSession2(noteModuleRequestDTO.getNoteSession2());
                if (noteModuleRequestDTO.getIdModule() != null) {
                    Module module = moduleRepository.findById(noteModuleRequestDTO.getIdModule()).orElseThrow(() -> new NoteModuleNotFoundException(noteModuleRequestDTO.getId()));
                    noteModule.setModule(module);
                }
                noteModule.setUpdatedOn(new Date());
                noteModuleRepository.save(noteModule);
                return noteModuleMapper.fromEntitytoResponseDTO(noteModule);

            } else throw new NoteModuleNotFoundException(noteModuleRequestDTO.getIdModule());

    }


    @Override
    public NoteModuleResponseDTO getNoteModule(Long id) throws NoteModuleNotFoundException {
        NoteModule noteModule = noteModuleRepository.findById(id).orElseThrow(() -> new NoteModuleNotFoundException(id));
        return noteModuleMapper.fromEntitytoResponseDTO(noteModule);
    }

    @Override
    public NoteModule getNoteModuleById(Long id) throws NoteModuleNotFoundException {
        NoteModule noteModule = noteModuleRepository.findById(id).orElseThrow(() -> new NoteModuleNotFoundException(id));
        if(noteModule == null){
            throw new NoteModuleNotFoundException(id);
        }
        return noteModule;
    }


    @Override
    public List<NoteModuleResponseDTO> getNotesModules() {
        List<NoteModule> noteModules = noteModuleRepository.findAll();
        List<NoteModuleResponseDTO> noteModulesResponses = new ArrayList<>();
        for(NoteModule noteModule : noteModules){
            NoteModuleResponseDTO noteModuleResponseDTO ;
            noteModuleResponseDTO = noteModuleMapper.fromEntitytoResponseDTO(noteModule);
            if(noteModule.getModule() !=null) noteModuleResponseDTO.setIdModule(noteModule.getModule().getIdModule());
            noteModulesResponses.add(noteModuleResponseDTO);
        }
        return noteModulesResponses;
    }

    @Override
    public void deleteNoteModule(Long id) throws NoteModuleNotFoundException {
        NoteModule noteModule = noteModuleRepository.findById(id).orElseThrow(()-> new NoteModuleNotFoundException(id));
        if(noteModule != null) noteModuleRepository.delete(noteModule);

    }
}
