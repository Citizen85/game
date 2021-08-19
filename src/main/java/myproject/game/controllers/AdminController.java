package myproject.game.controllers;

import io.swagger.annotations.ApiOperation;
import myproject.game.models.dto.GameDto;
import myproject.game.models.dto.QuestionDto;
import myproject.game.models.dto.UserDto;
import myproject.game.services.GameService;
import myproject.game.services.QuestionService;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private GameService gameService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;


    @PostMapping("/save/question")
    @ApiOperation(value = "Create new Question")
    public QuestionDto createQuestion(@RequestHeader("auth") String auth, @RequestBody QuestionDto questionDto){
        return questionService.saveQuestion(auth, questionDto);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update User-Data")
    public UserDto updateUser(@RequestHeader("auth") String auth, @RequestBody UserDto userDto){
        return userService.updateUser(auth, userDto);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "Find User by ID")
    public UserDto findById(@RequestParam Long id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all Users")
    public List<UserDto> getAllUsers(){
        return userService.findAllUsers();
    }

    @DeleteMapping("/user/delete")
    @ApiOperation(value = "Delete User by Id")
    public UserDto deleteUser(@RequestHeader("auth") String auth, @RequestParam Long id){
        return userService.deleteUser(auth, id);
    }

    @DeleteMapping("/game/delete")
    @ApiOperation(value = "Delete User by Id")
    public GameDto deleteGame(@RequestHeader("auth") String auth, @RequestParam Long gameId){
        return gameService.deleteGame(auth, gameId);
    }
}
