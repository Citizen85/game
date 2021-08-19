package myproject.game.controllers;

import io.swagger.annotations.ApiOperation;
import myproject.game.models.LoginWrapper;
import myproject.game.models.dto.UserDto;
import myproject.game.responses.LoginResponse;
import myproject.game.services.LoginService;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/v1")
public class LoginController {
    @Autowired
    private HttpSession session;
    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginWrapper loginWrapper){
        return loginService.login(loginWrapper);
    }

}
