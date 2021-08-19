package myproject.game.services.impl;

import myproject.game.dao.GameRepository;
import myproject.game.enums.GameStatus;
import myproject.game.enums.Status;
import myproject.game.mappers.GameMapper;
import myproject.game.mappers.QuestionMapper;
import myproject.game.mappers.UserMapper;
import myproject.game.models.dto.GameDto;
import myproject.game.models.dto.QuestionDto;
import myproject.game.models.dto.SessionDto;
import myproject.game.models.dto.UserDto;
import myproject.game.models.entities.Game;
import myproject.game.responses.GameResponse;
import myproject.game.responses.GameResult;
import myproject.game.services.GameService;
import myproject.game.services.QuestionService;
import myproject.game.services.SessionService;
import myproject.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SessionService sessionService;

    @Override
    public GameDto deleteGame(String auth, Long gameId) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        if(!sessionDto.getUser().isAdmin()){
            throw new RuntimeException("Not Allowed!!!");
        }
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game != null) {
            game.setStatus(Status.NOT_ACTIVE);
            game = gameRepository.save(game);
        }

        return GameMapper.INSTANCE.gameToGameDto(game);
    }

    @Override
    public GameDto findById(Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            throw new RuntimeException("Data is not found");
        }
        return GameMapper.INSTANCE.gameToGameDto(game);
    }

    @Override
    public GameDto updateVisibleWord(GameDto gameDto) {
        Game game = GameMapper.INSTANCE.gameDtoToGame(gameDto);
        game.setVisibleWord(gameDto.getVisibleWord());
        game = gameRepository.save(game);

        return GameMapper.INSTANCE.gameToGameDto(game);
    }

    @Override
    public GameResponse newGame(String auth) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        GameResponse gameResponse = new GameResponse();
        UserDto userDto = userService.findById(sessionDto.getUser().getId());
        QuestionDto questionDto = questionService.getRandomQuestionFromList();
        String visibleWord = String.join("", Collections.nCopies(questionDto.getAnswer().length(), "*"));

        Game game = new Game();
        game.setUser(UserMapper.INSTANCE.userDtoToUser(userDto));
        game.setQuestion(QuestionMapper.INSTANCE.qusetionDtoToQuestion(questionDto));

        game.setVisibleWord(visibleWord);
        game.setStatus(Status.ACTIVE);
        game.setGameStatus(GameStatus.ACTIVE);
        game = gameRepository.save(game);

        gameResponse.setGameId(game.getId());
        gameResponse.setQuestion(questionDto.getQuestn());

        return gameResponse;
    }

    @Override
    public GameResult currentGame(String auth, String guess) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        GameResult result = new GameResult();
        List<Game> games = gameRepository.findAllByUserIdAndGameStatus(sessionDto.getUser().getId(), GameStatus.ACTIVE);
        Game game = games.get(0);
        QuestionDto question = QuestionMapper.INSTANCE.questionToQuestionDto(game.getQuestion());
        String word = question.getAnswer();
        String clue = game.getVisibleWord();

        char clueArray[] = clue.toCharArray();

        if (guess.equals(word) || clue.equals(word)){
            game.setVisibleWord(guess);
            game.setGameStatus(GameStatus.WON);
            updateVisibleWord(GameMapper.INSTANCE.gameToGameDto(game));
            result.setQuestion(question.getQuestn());
            result.setMessage("You Won");
            result.setAnswer(word);
        } else {
            int index;
            if (!guess.equals(word)) {
                if (guess.length() == 1) {
                    index = 0;
                    index = word.indexOf(guess, index);

                    if (index != -1) {
                        clueArray[index] = guess.charAt(0);
                        index++;
                    }
                    clue = String.valueOf(clueArray);
                    guess = clue;
                    game.setVisibleWord(guess);
                    updateVisibleWord(GameMapper.INSTANCE.gameToGameDto(game));
                    result.setQuestion(question.getQuestn());
                    result.setMessage(guess);
                }
            }
        }

        return result;
    }

    @Override
    public List<GameDto> findAll() {
        List<Game> games = gameRepository.findAll();
        return GameMapper.INSTANCE.gamesToGameDtos(games);
    }

    @Override
    public List<GameDto> findAllByUserIdAndGameStatus(Long id, GameStatus status) {
        List<Game> games = gameRepository.findAllByUserIdAndGameStatus(id, status);
        return GameMapper.INSTANCE.gamesToGameDtos(games);
    }

    @Override
    public List<GameDto> getUserWonGames(String auth) {
        SessionDto sessionDto = sessionService.getSessionInfo(auth);
        List<Game> games = gameRepository.findAllByUserIdAndGameStatus(sessionDto.getUser().getId(), GameStatus.WON);
        return GameMapper.INSTANCE.gamesToGameDtos(games);
    }

}
