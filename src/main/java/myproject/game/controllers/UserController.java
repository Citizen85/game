package myproject.game.controllers;

import io.swagger.annotations.ApiOperation;
import myproject.game.models.dto.UserDto;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ApiOperation(value = "Create User")
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

}
