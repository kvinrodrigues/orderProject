package py.com.poraplz.cursomc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import py.com.poraplz.cursomc.dto.forgot.EmailDTO;
import py.com.poraplz.cursomc.dto.forgot.ForgotPassDTO;
import py.com.poraplz.cursomc.dto.forgot.PasswordDto;
import py.com.poraplz.cursomc.exceptions.AuthorizationException;
import py.com.poraplz.cursomc.security.JWTUtil;
import py.com.poraplz.cursomc.security.User;
import py.com.poraplz.cursomc.services.AuthService;
import py.com.poraplz.cursomc.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    AuthService iForgot;

    @Autowired
    JWTUtil jwtUtil;

    //TODO enviar url del servicio
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPassDTO request, HttpServletRequest req){
        return  ResponseEntity.ok().body(iForgot.forgotPassword(request.getEmail()));
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> passwordChange(@Valid @RequestBody PasswordDto req){
        return ResponseEntity.ok(iForgot.changePasswordProcess(req));
    }

    @RequestMapping(value="/refresh_token", method=RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        User user = UserService.getLoggedUser();
        if(user == null)
            throw new AuthorizationException("Acceso denegado");
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    /**
     * Cambiar y envia el nuevo password al email requerido
     * @param request
     * @return
     */
    @RequestMapping(value="/v1/forgot", method=RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid EmailDTO request) {
        iForgot.sendNewPassword(request.getEmail());
        return ResponseEntity.noContent().build();

    }

}
