package myproject.game.mappers;

import myproject.game.models.dto.GameDto;
import myproject.game.models.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    Game gameDtoToGame (GameDto gameDto);
    GameDto gameToGameDto (Game game);

    List<Game> gameDtosToGames (List<GameDto> gameDtos);
    List<GameDto> gamesToGameDtos (List<Game> games);
}
