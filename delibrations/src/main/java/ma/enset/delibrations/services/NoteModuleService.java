package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.NoteModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.NoteModuleResponseDTO;
import ma.enset.delibrations.entities.NoteModule;
import ma.enset.delibrations.entities.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteModuleNotFoundException;

import java.util.List;

public interface NoteModuleService {
    NoteModuleResponseDTO createNoteModule(NoteModuleRequestDTO noteModuleDTO ) throws NoteModuleNotFoundException, ModuleNotFoundException;

    NoteModuleResponseDTO updateNoteModule(NoteModuleRequestDTO noteModuleRequestDTO) throws NoteModuleNotFoundException,ModuleNotFoundException;

    NoteModuleResponseDTO getNoteModule (Long id) throws NoteModuleNotFoundException;
    NoteModule getNoteModuleById(Long id) throws NoteModuleNotFoundException;
    List<NoteModuleResponseDTO> getNotesModules();
    void deleteNoteModule(Long id) throws NoteModuleNotFoundException;
    NoteModuleResponseDTO getNoteModuleByModule(Long idModule, Long idEtu);
}
