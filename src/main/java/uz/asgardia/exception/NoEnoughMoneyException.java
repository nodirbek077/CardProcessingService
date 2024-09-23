package uz.asgardia.exception;

public class NoEnoughMoneyException extends RuntimeException{
    public NoEnoughMoneyException(String message){
        super(message);
    }
}
