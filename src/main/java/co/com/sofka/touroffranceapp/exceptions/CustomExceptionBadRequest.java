package co.com.sofka.touroffranceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomExceptionBadRequest extends RuntimeException{

    private static final long serialVersionUID = 7478152478564113226L;

    public CustomExceptionBadRequest(String message){
        super(message);
    }
}
