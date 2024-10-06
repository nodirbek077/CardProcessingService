package uz.asgardia.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardLimitExceededException.class)
    public ResponseEntity<Map<String, String>> handleCardLimitExceeded(CardLimitExceededException ex){
        Map<String,String> response = new HashMap<>();
        response.put("code", "limit_exceeded");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("code", "missing_field");
        response.put("message", "Missing required field(s)");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardIdNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCardIdNotFound(CardIdNotFoundException ex){
        Map<String,String> response = new HashMap<>();
        response.put("code", "not_found");
        response.put("message", "Card with such id not exists in processing");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
