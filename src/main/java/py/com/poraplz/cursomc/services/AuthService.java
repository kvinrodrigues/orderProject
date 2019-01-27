package py.com.poraplz.cursomc.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.dto.forgot.PasswordDto;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.enums.Perfil;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.security.JWTUtil;

import java.util.Random;
import java.util.Set;

@Service
public class AuthService {
    private ClienteService clienteService;
    private JWTUtil jwtUtil;
    private BCryptPasswordEncoder encoder;
    private EmailService emailService;
    private Random rand = new Random();

    public AuthService(ClienteService clienteService, JWTUtil jwtUtil, BCryptPasswordEncoder encoder, EmailService emailService){
        this.clienteService = clienteService;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.emailService = emailService;
    }

    public String forgotPassword(String username){
        String token = jwtUtil.generateToken(username);
        Cliente cliente = clienteService.getByEmail(username);
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
        Cliente cliente = clienteService.getByEmail(email);
        cliente.setPassword(encoder.encode(dto.getNewPassword()));
        clienteService.saveOrUpdate(cliente);
        return "se cambia pass";
    }


    public void sendNewPassword(String email){
        Cliente cliente = clienteService.getByEmail(email);
        if(cliente == null){
            throw new ObjectNotFoundException("Email no encontrado");
        }
        String newPass = newPassword();
        cliente.setPassword(encoder.encode(newPass));
        clienteService.saveOrUpdate(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword(){
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar(){
        //Tres posibles valores
        int opt = rand.nextInt(3);
        //Digito
        if(opt == 0){
            return (char) (rand.nextInt(10) + 48);
        }else if(opt ==1){ //Letra mayuscula
            return (char) (rand.nextInt(26) + 65);
        }else{ //letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }


    }
}
