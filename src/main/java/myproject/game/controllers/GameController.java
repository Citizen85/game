package myproject.game.controllers;

import io.swagger.annotations.ApiOperation;
import myproject.game.config.BaseController;
import myproject.game.models.dto.GameDto;
import myproject.game.models.dto.QuestionDto;
import myproject.game.responses.GameResponse;
import myproject.game.responses.GameResult;
import myproject.game.services.GameService;
import myproject.game.services.QuestionService;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController  {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private GameService gameService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @PostMapping("/newGame")
    @ApiOperation(value = "Creating new game")
    public GameResponse newGame(@RequestHeader("auth") String auth){
        return gameService.newGame(auth);
    }

    @PostMapping("/currentGame")
    @ApiOperation(value = "Play created game")
    public GameResult currentGame(@RequestHeader("auth") String auth, @RequestParam String guess){
        return gameService.currentGame(auth, guess);
    }

    @GetMapping("/userGames")
    @ApiOperation(value = "User's list of games")
    public List<GameDto> gamesList(@RequestHeader("auth") String auth){
        return gameService.getUserWonGames(auth);
    }


}
