package myproject.game.dao;

import myproject.game.enums.Status;
import myproject.game.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByStatus (Status status);
    User findByUserNameAndPwd (String userName, String pwd);
    User findByIsAdmin(boolean isAdmin);
}
