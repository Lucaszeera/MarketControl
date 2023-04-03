package br.com.fiap.marketcontrol.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.fiap.marketcontrol.models.RestValidationError;

@RestControllerAdvice
public class RestExceptionHandler {
    
    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestValidationError>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        logger.error("Erro de argumento inválido");
        List<RestValidationError> errorsList = new ArrayList<>();
        e.getFieldErrors().forEach(err -> errorsList.add(new RestValidationError(400, err.getField(), err.getDefaultMessage())));
        
        
        return ResponseEntity.badRequest().body(errorsList);
    }
}
