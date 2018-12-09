package py.com.poraplz.cursomc.services.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import py.com.poraplz.cursomc.dto.FieldMessage;
import py.com.poraplz.cursomc.dto.client.ClientDTO;
import py.com.poraplz.cursomc.services.ClienteService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    ClienteService service;

    @Autowired
    HttpServletRequest request;

    @Override
    public void initialize(ClientUpdate ann) {
    }
    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));
        List<FieldMessage> list = new ArrayList<>();
        if(!uriId.equals(service.getClientByEmail(objDto.getEmail()).getId())){
            list.add(new FieldMessage("email","Email no disponible"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
