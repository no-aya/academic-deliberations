package ma.enset.delibrations.services.servicesImp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.mappers.SessionMapper;
import ma.enset.delibrations.dtos.requests.SessionRequestDTO;
import ma.enset.delibrations.dtos.responses.SessionResponseDTO;
import ma.enset.delibrations.entities.Session;
import ma.enset.delibrations.exceptions.SessionNotFoundException;
import ma.enset.delibrations.repositories.SessionRepository;
import ma.enset.delibrations.services.SessionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {


    private SessionMapper sessionMapper;
    private SessionRepository sessionRepository;


    @Override
    public SessionResponseDTO createSession(SessionRequestDTO sessionRequestDTO) {
        if (sessionRequestDTO == null) {
            throw new IllegalArgumentException("SessionRequestDTO is null");
        }
        Session session = sessionMapper.fromRequestDTOtoEntity(sessionRequestDTO);
        session.setClosed(false);
        session = sessionRepository.save(session);
        return sessionMapper.fromEntityToResponseDTO(session);
    }

    @Override
    public void closeSession(Long id){
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Session session = sessionRepository.findById(id).get();
        session.setClosed(!session.isClosed());
        session = sessionRepository.save(session);

    }


    @Override
    public SessionResponseDTO getSession(Long id) {
        if (id!=null){
            Session session = sessionRepository.findById(id).get();
            return sessionMapper.fromEntityToResponseDTO(session);
        }
        throw new IllegalArgumentException("id is null");
    }

    @Override
    public List<SessionResponseDTO> getSessions() {
        List<Session> sessions = sessionRepository.findAll();
        List<SessionResponseDTO> sessionResponseDTOS = new ArrayList<>();
        for (Session session : sessions) {
            sessionResponseDTOS.add(sessionMapper.fromEntityToResponseDTO(session));
        }
        return sessionResponseDTOS;
    }


    @Override
    public Session updateSession(Session session) throws SessionNotFoundException {
        Session s = sessionRepository.findById(session.getId()).orElseThrow(() -> new SessionNotFoundException("Session not found for this id :: " + session.getId()));
        s.setClosed(session.isClosed());
        s.setLibelle(session.getLibelle());
        s.setDateDebut(session.getDateDebut());
        s.setDateFin(session.getDateFin());
        return sessionRepository.save(s);
    }

    @Override
    public void deleteSession(Long id) {
        if (id!=null){
            sessionRepository.deleteById(id);
        }
        throw new IllegalArgumentException("id is null");

    }
}
