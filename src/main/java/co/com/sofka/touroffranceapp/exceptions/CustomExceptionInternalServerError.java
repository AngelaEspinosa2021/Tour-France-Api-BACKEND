package co.com.sofka.touroffranceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomExceptionInternalServerError extends RuntimeException{

    private static final long serialVersionUID = -1121780651598940958L;

    public CustomExceptionInternalServerError(String message){
        super(message);
    }
}
