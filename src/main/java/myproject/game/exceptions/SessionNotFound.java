package myproject.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class SessionNotFound extends RuntimeException {

    public SessionNotFound(String message) {
        super(message);
    }
}