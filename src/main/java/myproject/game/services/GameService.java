package myproject.game.services;

import myproject.game.enums.GameStatus;
import myproject.game.models.dto.GameDto;
import myproject.game.models.dto.QuestionDto;
import myproject.game.models.entities.Question;
import myproject.game.responses.GameResponse;
import myproject.game.responses.GameResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GameService {

    GameDto deleteGame (String auth, Long id);
    GameDto findById(Long id);
    GameDto updateVisibleWord(GameDto gameDto);

    GameResponse newGame(String auth);
    GameResult currentGame(String auth, String word);

    List<GameDto> findAll();
    List<GameDto> findAllByUserIdAndGameStatus(Long id, GameStatus status);
    List<GameDto> getUserWonGames(String auth);


}
