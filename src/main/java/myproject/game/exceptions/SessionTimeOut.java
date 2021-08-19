package myproject.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class SessionTimeOut extends RuntimeException {
    public SessionTimeOut(String message){
        super(message);
    }
}