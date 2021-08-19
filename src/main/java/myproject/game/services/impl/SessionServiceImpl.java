package myproject.game.services.impl;

import myproject.game.dao.SessionRepository;
import myproject.game.exceptions.SessionNotFound;
import myproject.game.exceptions.SessionTimeOut;
import myproject.game.mappers.SessionMapper;
import myproject.game.mappers.UserMapper;
import myproject.game.models.dto.SessionDto;
import myproject.game.models.dto.UserDto;
import myproject.game.models.entities.Session;
import myproject.game.models.entities.User;
import myproject.game.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;


    @Override
    public SessionDto generateToken(UserDto userDto) {
        String token = UUID.randomUUID().toString();

        User user = UserMapper.INSTANCE.userDtoToUser(userDto);

        List<Session> sessions = sessionRepository.findAllByUserAndEndDateIsNull(user);

        Session session = sessions.size() == 0 ? null : sessions.get(0);


        if (session != null){
            sessions.stream()
                    .forEach(x->x.setEndDate(new Date()));
        }

        session = new Session();

        session.setToken(token);
        session.setUser(user);
        session.setStartDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);

        session.setEndDate(calendar.getTime());
        session = sessionRepository.save(session);

        return SessionMapper.INSTANCE.sessionToSessionDto(session);
    }

    @Override
    public SessionDto getSessionInfo(String token) {
        Session session = sessionRepository.findByToken(token);

        if (session == null){
            throw new SessionNotFound("Session not found");
        }

        if(session.getEndDate().after(new Date())){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 10);
            session.setEndDate(calendar.getTime());
            sessionRepository.save(session);
        }else {
            throw new SessionTimeOut("Session timed out!");
        }

        SessionDto sessionDto = SessionMapper.INSTANCE.sessionToSessionDto(session);

        return sessionDto;
    }
}
