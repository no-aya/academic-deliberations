package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.SessionRequestDTO;
import ma.enset.delibrations.dtos.responses.SessionResponseDTO;
import ma.enset.delibrations.entities.Session;
import ma.enset.delibrations.entities.exceptions.SessionNotFoundException;

import java.util.List;

public interface SessionService {

    SessionResponseDTO createSession(SessionRequestDTO sessionRequestDTO);
    void closeSession(Long id);
    SessionResponseDTO getSession(Long id);
    List<SessionResponseDTO> getSessions();

    //SessionResponseDTO updateSession(Long id, SessionRequestDTO sessionRequestDTO) throws SessionNotFoundException;
    Session updateSession(Session session) throws SessionNotFoundException;
    void deleteSession(Long id);

}
