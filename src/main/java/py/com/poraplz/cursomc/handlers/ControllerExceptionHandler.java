package py.com.poraplz.cursomc.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import py.com.poraplz.cursomc.dto.StandartError;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
        StandartError sdtError = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sdtError);
    }
}
