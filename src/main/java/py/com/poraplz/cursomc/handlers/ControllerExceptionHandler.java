package py.com.poraplz.cursomc.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import py.com.poraplz.cursomc.dto.StandartError;
import py.com.poraplz.cursomc.dto.ValidationError;
import py.com.poraplz.cursomc.exceptions.DataIntegrityException;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
        StandartError sdtError = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sdtError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandartError> constraintViolationException(DataIntegrityException e, HttpServletRequest request){
        StandartError standartError = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> argumentValidation(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error de validacion",
                System.currentTimeMillis());
        for(FieldError x : e.getBindingResult().getFieldErrors()){
            validationError.addError(x.getField(), x.getDefaultMessage());
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);

    }



}
