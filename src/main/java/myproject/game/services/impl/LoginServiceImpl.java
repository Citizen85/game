package myproject.game.services.impl;

import myproject.game.mappers.UserMapper;
import myproject.game.models.LoginWrapper;
import myproject.game.models.dto.SessionDto;
import myproject.game.models.dto.UserDto;
import myproject.game.models.entities.User;
import myproject.game.responses.LoginResponse;
import myproject.game.services.LoginService;
import myproject.game.services.SessionService;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @Override
    public LoginResponse login(LoginWrapper loginWrapper) {

        LoginResponse loginResponse = new LoginResponse();
        UserDto userDto = userService.findByUserNameAndPwd(loginWrapper.getLogin(), loginWrapper.getPassword());

        if(userDto==null){
            loginResponse.setStatus(0);
            loginResponse.setMessage("Пользователь не найден. Неверный логин или пароль");
            return loginResponse;        }

        SessionDto sessionDto = sessionService.generateToken(userDto);

        if(sessionDto == null){
            loginResponse.setStatus(0);
            loginResponse.setMessage("Сессия не найдена");
            return loginResponse;
        }

        loginResponse.setStatus(1);
        loginResponse.setMessage("Успешно");
        loginResponse.setUser(userDto);
        loginResponse.setToken(sessionDto.getToken());


        return loginResponse;
    }

}
