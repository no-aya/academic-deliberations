package ma.enset.delibrations.services.servicesImp;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.ModuleMapper;
import ma.enset.delibrations.dtos.mappers.NoteModuleMapper;
import ma.enset.delibrations.dtos.requests.ModuleRequestDTO;
import ma.enset.delibrations.dtos.responses.ModuleResponseDTO;
import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.exceptions.ModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.NoteModuleNotFoundException;
import ma.enset.delibrations.entities.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.services.ModuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.CannotProceedException;
import java.util.*;
import java.util.stream.Collectors;


@Service @Transactional @AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    //id est le code du module
    private ModuleRepository moduleRepository;
    private ModuleMapper moduleMapper;

    private NoteModuleMapper noteModuleMapper;
    private NoteModuleServiceImpl noteModuleService;
    private SemestreRepository semestreRepository;
    private FiliereRepository filiereRepository;
    private ProfesseurRepository professeurRepository;
    private ElementRepository elementRepository;

    private String generateCode() {
        //TODO: Generate code based on the "Module", "Filiere" and "Semestre" attributes
        return "CODE"+System.currentTimeMillis();
    }

    @Override
    public ModuleResponseDTO createModule(ModuleRequestDTO moduleRequestDTO) throws NoteModuleNotFoundException, CannotProceedException {
       if(moduleRequestDTO!=null) {
           Module module = moduleMapper.fromRequestDTOtoEntity(moduleRequestDTO);
           if (moduleRequestDTO.getNoteModules() != null) {
               Long[] notesModules = moduleRequestDTO.getNoteModules();
               List<NoteModule> noteModules = new ArrayList<>();
               for (Long idNoteModule : notesModules) {
                   NoteModule noteModule =  noteModuleService.getNoteModuleById(idNoteModule);
                   noteModules.add(noteModule);
               }
               module.setCode(generateCode());
               module.setNoteModules(noteModules);
           }
           moduleRepository.save(module);
           return moduleMapper.fromEntitytoResponseDTO(module);
       }
       throw new CannotProceedException("module is null");
    }

    @Override
    public ModuleResponseDTO updateModule(String id,ModuleRequestDTO moduleRequestDTO) throws ModuleNotFoundException, NoteModuleNotFoundException, SemestreNotFoundException {
        if(id != null && moduleRequestDTO != null) {
            Module module = moduleRepository.findByCodeAndSoftDeleteIsFalse(id);
            if (module == null) throw new ModuleNotFoundException(id);
            if(moduleRequestDTO.getCode()!=null)module.setCode(moduleRequestDTO.getCode());
            if (moduleRequestDTO.getIntitule() != null) module.setIntitule(moduleRequestDTO.getIntitule());
            if(moduleRequestDTO.getNoteModules()!=null){
                Long[] notesModules = moduleRequestDTO.getNoteModules();
                List<NoteModule>noteModules=new ArrayList<>();
                for(Long noteModuleId : notesModules){
                    NoteModule noteModule = noteModuleService.getNoteModuleById(noteModuleId);
                    noteModules.add(noteModule);
                }
                module.setNoteModules(noteModules);
                for (NoteModule noteModule:noteModules) {
                    noteModule.setModule(module);
                    noteModuleService.updateNoteModule(noteModuleMapper.fromEntitytoRequestDTO(noteModule));
                }
            }
            //semestre
            if(moduleRequestDTO.getSemestreId()!=null){
                Semestre semestre = semestreRepository.findById(moduleRequestDTO.getSemestreId()).orElseThrow(()->new SemestreNotFoundException(moduleRequestDTO.getSemestreId()));
                module.setSemestre(semestre);
            }

            moduleRepository.save(module);
            return moduleMapper.fromEntitytoResponseDTO(module);
        }
        throw new ModuleNotFoundException(id);
    }

    @Override
    public ModuleResponseDTO getModuleByCode(String code){
        if(code !=null) {
            Module module = moduleRepository.findByCodeAndSoftDeleteIsFalse(code);
            if (module != null) {
                ModuleResponseDTO moduleResponseDTO = moduleMapper.fromEntitytoResponseDTO(module);
                Semestre semestre = module.getSemestre();
                if (semestre != null) moduleResponseDTO.setSemestre(semestre.getId());
                return moduleResponseDTO;
            }
        }
        return null;

    }

    public ModuleResponseDTO getModule(String id) throws ModuleNotFoundException{
        if(id == null) return null;
        Module module = moduleRepository.findByCodeAndSoftDeleteIsFalse(id);
        if(module == null) throw  new ModuleNotFoundException(id);
        return moduleMapper.fromEntitytoResponseDTO(module);
    }


    @Override
    public List<ModuleResponseDTO> getModules() {
        List<Module> moduleEtudiants = moduleRepository.findBySoftDeleteIsFalse();
        List<ModuleResponseDTO> modulesResponse = new ArrayList<>();
        for(Module m : moduleEtudiants){
            ModuleResponseDTO responseDTO = moduleMapper.fromEntitytoResponseDTO(m);
           if (m.getSemestre()!=null){
               responseDTO.setSemestre(m.getSemestre().getId());
           }

            modulesResponse.add(responseDTO);

        }
        return modulesResponse;
    }

    @Override
    public void deleteModule(String id) throws ModuleNotFoundException{
        Module module = moduleRepository.findByCodeAndSoftDeleteIsFalse(id);
        if(module == null) throw new ModuleNotFoundException(id);
        else {
            module.setSoftDelete(true);
            moduleRepository.save(module);
        }
    }

    @Override
    public List<ModuleResponseDTO> getModuleWithFiliereAndProf(Long idProf, Long idFiliere, String libelSemestre) throws ModuleNotFoundException {
        if(idProf!=null && idFiliere!=null){
            Filiere filiere= filiereRepository.findByIdAndSoftDeleteIsFalse(idFiliere);
            Professeur prof = professeurRepository.findByIdAndSoftDeleteIsFalse(idProf);
            Semestre semestre = semestreRepository.findByLibelle(libelSemestre);
            if (filiere!=null && prof!=null && semestre!=null){
                List<Element> elements = elementRepository.findByCleEtrangere(idProf);
                if(elements!=null){
                    List<Module> modules = new ArrayList<>();
                    for (Element e: elements) {
                        Module module = moduleRepository.findById(e.getModule().getId()).orElseThrow( ()-> new ModuleNotFoundException(e.getModule().getId()));
                        if (module!=null && module.getFiliere().getId()==idFiliere  && module.getSemestre().getId()==semestre.getId() ){
                            modules.add(module);
                        }
                    }
                    List<ModuleResponseDTO> responses = modules.stream()
                            .map(f -> moduleMapper.fromEntitytoResponseDTO(f))
                            .collect(Collectors.toList());

                    Set<ModuleResponseDTO> uniqueResponses = new HashSet<>(responses);
                    return new ArrayList<>(uniqueResponses);
                }
            }
        }
        return null;
    }
}
