package ma.enset.delibrations.controllers;


import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.SessionRequestDTO;
import ma.enset.delibrations.dtos.responses.SessionResponseDTO;
import ma.enset.delibrations.exceptions.SessionNotFoundException;
import ma.enset.delibrations.services.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class SessionController {

    private SessionService sessionService;

    @GetMapping("/sessions")
    public List<SessionResponseDTO> getAllSessions(){
        return sessionService.getSessions();
    }

    @GetMapping("/{id}")
    public SessionResponseDTO getSession(@PathVariable Long id){
        return sessionService.getSession(id);
    }

    @PostMapping("/sessions")
    public SessionResponseDTO createSession(@RequestBody SessionRequestDTO sessionRequestDTO){
        return sessionService.createSession(sessionRequestDTO);
    }

    @PutMapping("/sessions/{id}")
    public SessionResponseDTO updateSession(@PathVariable Long id, @RequestBody SessionRequestDTO sessionRequestDTO) throws SessionNotFoundException {
        return sessionService.updateSession(id, sessionRequestDTO);
    }

    @DeleteMapping("/sessions/{id}")
    public void deleteSession(@PathVariable Long id){
        if (id != null) sessionService.deleteSession(id);
    }





}
