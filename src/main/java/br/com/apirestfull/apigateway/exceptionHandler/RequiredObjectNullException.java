package br.com.apirestfull.apigateway.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectNullException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RequiredObjectNullException(String exception) {
        super(exception);
    }

    public RequiredObjectNullException() {
        super("Null object it is not allowed to be saved");
    }
}
