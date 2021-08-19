package myproject.game.services;

import myproject.game.models.LoginWrapper;
import myproject.game.responses.LoginResponse;

public interface LoginService {

   LoginResponse login(LoginWrapper loginWrapper);
}
