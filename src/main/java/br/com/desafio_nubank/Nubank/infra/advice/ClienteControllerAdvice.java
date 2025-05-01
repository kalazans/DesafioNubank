package br.com.desafio_nubank.Nubank.infra.advice;

import br.com.desafio_nubank.Nubank.infra.advice.customError.CamposJsonInvalido;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ClienteControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> idInvalido(BadRequestException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CamposJsonInvalido>>camposJsonInvalidos(MethodArgumentNotValidException ex){
        List<CamposJsonInvalido> error =  ex.getFieldErrors().stream().map(e-> new CamposJsonInvalido(e.getField(),e.getDefaultMessage())).toList();
        return ResponseEntity.unprocessableEntity().body(error);
    }
}
