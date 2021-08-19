package myproject.game.dao;

import myproject.game.models.entities.Session;
import myproject.game.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByUserAndEndDateIsNull(User user);

    Session findByToken(String token);
}
