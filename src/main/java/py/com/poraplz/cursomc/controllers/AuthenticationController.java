package py.com.poraplz.cursomc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.poraplz.cursomc.dto.forgot.ForgotPassDTO;
import py.com.poraplz.cursomc.dto.forgot.PasswordDto;
import py.com.poraplz.cursomc.services.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    AuthService iForgot;

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPassDTO request, HttpServletRequest req){
        return  ResponseEntity.ok().body(iForgot.forgotPassword(request.getEmail()));
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> passwordChange(@Valid @RequestBody PasswordDto req){
        return ResponseEntity.ok(iForgot.changePasswordProcess(req));
    }


}
