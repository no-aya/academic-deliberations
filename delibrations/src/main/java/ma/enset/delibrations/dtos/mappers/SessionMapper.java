package ma.enset.delibrations.dtos.mappers;

import lombok.Data;
import ma.enset.delibrations.dtos.requests.SessionRequestDTO;
import ma.enset.delibrations.dtos.responses.SessionResponseDTO;
import ma.enset.delibrations.entities.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public SessionRequestDTO fromEntitytoRequestDTO(Session session){
        SessionRequestDTO sessionRequestDTO = new SessionRequestDTO();
        BeanUtils.copyProperties(session, sessionRequestDTO);
        return sessionRequestDTO;
    }
    public Session fromRequestDTOtoEntity(SessionRequestDTO sessionRequestDTO){
        Session session = new Session();
        BeanUtils.copyProperties(sessionRequestDTO, session);
        return session;
    }

    //fromEntityToResponseDTO
    public SessionResponseDTO fromEntityToResponseDTO(Session session){
        SessionResponseDTO sessionResponseDTO = new SessionResponseDTO();
        BeanUtils.copyProperties(session, sessionResponseDTO);
        return sessionResponseDTO;
    }

    public Session fromResponseDTOtoEntity(SessionResponseDTO sessionResponseDTO){
        Session session = new Session();
        BeanUtils.copyProperties(sessionResponseDTO, session);
        return session;
    }


}
