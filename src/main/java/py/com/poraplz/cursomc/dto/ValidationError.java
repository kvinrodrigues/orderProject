package py.com.poraplz.cursomc.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError{

    private List<FieldMessage> errors = new ArrayList();

    public ValidationError(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;

    }

    public void addError(String name, String message){
        errors.add(new FieldMessage(name, message));

    }
}
