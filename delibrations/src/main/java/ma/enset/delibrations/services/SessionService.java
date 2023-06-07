package ma.enset.delibrations.services;

import ma.enset.delibrations.dtos.requests.SessionRequestDTO;
import ma.enset.delibrations.dtos.responses.SessionResponseDTO;
import ma.enset.delibrations.exceptions.SessionNotFoundException;

import java.util.List;

public interface SessionService {

    SessionResponseDTO createSession(SessionRequestDTO sessionRequestDTO);
    void closeSession(SessionRequestDTO sessionRequestDTO);
    SessionResponseDTO getSession(Long id);
    List<SessionResponseDTO> getSessions();

    SessionResponseDTO updateSession(Long id, SessionRequestDTO sessionRequestDTO) throws SessionNotFoundException;

    void deleteSession(Long id);

}
