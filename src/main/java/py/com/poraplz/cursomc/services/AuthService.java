package py.com.poraplz.cursomc.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.dto.forgot.PasswordDto;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.enums.Perfil;
import py.com.poraplz.cursomc.security.JWTUtil;

import java.util.Set;

@Service
public class AuthService {
    private ClienteService clienteService;
    private JWTUtil jwtUtil;
    private BCryptPasswordEncoder encoder;
    private EmailService emailService;

    public AuthService(ClienteService clienteService, JWTUtil jwtUtil, BCryptPasswordEncoder encoder, EmailService emailService){
        this.clienteService = clienteService;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.emailService = emailService;
    }

    public String forgotPassword(String username){
        String token = jwtUtil.generateToken(username);
        Cliente cliente = clienteService.getClientByEmail(username);
        String forgotToken = null;

        Set<Perfil> profiles = cliente.getProfiles();
        if(profiles.contains(Perfil.FORGOT)){
            forgotToken = cliente.getForgotPassToken();
            if(forgotToken == null || !jwtUtil.isValidExpirationTime(forgotToken)){
                cliente.setForgotPassToken(token);
                forgotToken = token;
            }
        }else {
            cliente.setForgotPassToken(null);
        }
        clienteService.saveOrUpdate(cliente);
        emailService.sendChangeOfPassword(token);
        return forgotToken;

    }

    //Se pasa email obtenido desde el token y el nuevo password
    //implementar para recibir clave para confirmar cambio de password
    public Object changePasswordProcess(PasswordDto dto){
        String email = jwtUtil.getUserName(dto.getToken());
        Cliente cliente = clienteService.getClientByEmail(email);
        cliente.setPassword(encoder.encode(dto.getNewPassword()));
        clienteService.saveOrUpdate(cliente);
        return "se cambia pass";
    }


}
