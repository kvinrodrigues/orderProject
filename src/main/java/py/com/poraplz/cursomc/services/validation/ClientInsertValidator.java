package py.com.poraplz.cursomc.services.validation;


import org.springframework.beans.factory.annotation.Autowired;
import py.com.poraplz.cursomc.dto.FieldMessage;
import py.com.poraplz.cursomc.dto.client.ClientNewDTO;
import py.com.poraplz.cursomc.entities.enums.TipoCliente;
import py.com.poraplz.cursomc.services.ClienteService;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    ClienteService service;

    @Override
    public void initialize(ClientInsert ann) {
    }
    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if(objDto.getFirstPhone() == null){
            list.add(new FieldMessage("firstPhone","El primer numero de telefono no puede ser nulo"));
        }

        //TODO validar persona fisica y juridica paraguay
        if(TipoCliente.PERSONA_FISICA.equals(TipoCliente.toEnum(objDto.getType()))){

        }

        if(service.getByEmail(objDto.getEmail()) != null){
            list.add(new FieldMessage("email", "Email ya utilizado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
