package br.com.apidiscount.infra.exception;

import br.com.apidiscount.infra.exception.enumerator.ErrorMappingEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiDiscountException extends RuntimeException {

    private String message;
    private String developMessage;
    private HttpStatus status;

    public ApiDiscountException(String message, HttpStatus status) {
        super();
        this.message = message;
        this.status = status;
        this.developMessage = message;
    }

    public ApiDiscountException(ErrorMappingEnum error, HttpStatus status) {
        super();
        this.message = error.getMessage();
        this.status = status;
        this.developMessage = error.getMessage();
    }

}
