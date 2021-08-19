package myproject.game.services;

import myproject.game.models.dto.SessionDto;
import myproject.game.models.dto.UserDto;

public interface SessionService {

    SessionDto generateToken(UserDto userDto);

    SessionDto getSessionInfo(String token);
}
