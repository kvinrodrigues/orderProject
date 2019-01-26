package py.com.poraplz.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.security.User;

@Service
public class UserService {
    private static final Logger  logger = LoggerFactory.getLogger(UserService.class);
    public static User getLoggedUser() {

        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        } catch (Exception e) {
            logger.error("Error al obtener usuario logueado");
            return null;
        }
    }

}
