package br.com.apidiscount.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApiDiscountException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ApiDiscountException api) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(api.getStatus().value());
        errorDetail.setTitle("Erro ao processar");
        errorDetail.setDetail(api.getMessage());
        errorDetail.setDeveloperMessage(api.getDevelopMessage());

        return new ResponseEntity<>(errorDetail, null, api.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> requestHandlingNoHandlerFound(final NoHandlerFoundException ex) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Rota não encontrada");
        errorDetail.setDetail("Não foi possível acessar o caminho especificado");
        errorDetail.setDeveloperMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

}