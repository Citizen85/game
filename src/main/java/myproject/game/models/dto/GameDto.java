package myproject.game.models.dto;

import lombok.Data;
import myproject.game.enums.GameStatus;
import myproject.game.enums.Status;


public class GameDto {
    private Long id;
    private UserDto user;
    private QuestionDto question;
    private String visibleWord;
    private Status status;
    private GameStatus gameStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public void setVisibleWord(String visibleWord) {
        this.visibleWord = visibleWord;
    }
}
