package myproject.game.mappers;

import myproject.game.models.dto.SessionDto;
import myproject.game.models.entities.Session;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SessionMapper {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    Session sessionDtoToSession(SessionDto sessionDto);
    SessionDto sessionToSessionDto(Session session);

    List<Session> sessionDtosToSessions(List<SessionDto> sessionDtos);
    List<SessionDto> sessionsToSessionDtos(List<Session> sessions);
}
