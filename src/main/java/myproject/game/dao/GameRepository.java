package myproject.game.dao;

import myproject.game.enums.GameStatus;
import myproject.game.enums.ResultType;
import myproject.game.enums.Status;
import myproject.game.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByStatus (Status status);
//    List<Game> findAllByResultType (ResultType resultType);
    List<Game> findAllByUserIdAndGameStatus(Long userId, GameStatus status);
}
