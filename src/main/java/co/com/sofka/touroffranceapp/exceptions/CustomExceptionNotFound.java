package co.com.sofka.touroffranceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomExceptionNotFound extends RuntimeException{

    private static final long serialVersionUID = -5620959139754471404L;

    public CustomExceptionNotFound(String message){
        super(message);
    }
}
