package uz.asgardia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardIdNotFoundException extends RuntimeException {
    public CardIdNotFoundException(String message){
        super(message);
    }
}
