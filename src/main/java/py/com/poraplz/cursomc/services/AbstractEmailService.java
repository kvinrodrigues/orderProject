package py.com.poraplz.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.Pedido;
import py.com.poraplz.cursomc.security.JWTUtil;

import java.util.Date;

@Service
public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private String fromEmail;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ClienteService clienteService;


    @Override
    public void sendOrderConfirmation(Pedido order){
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
        sendEmail(sm);
    }

    //TODO Mejorar mensaje a enviar
    @Override
    public void sendChangeOfPassword(String token){
        String username = jwtUtil.getUserName(token);
        Cliente cliente = clienteService.getClientByEmail(username);
        SimpleMailMessage mailMessage = prepareSimpleMailMessageFromCliente(cliente);
        sendEmail(mailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Pedido order){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom(fromEmail);
        sm.setTo(order.getClient().getEmail());
        sm.setSubject("Pedido confirmado! Codigo: "+ order.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(order.toString());
        return sm;

    }

    protected SimpleMailMessage prepareSimpleMailMessageFromCliente(Cliente value){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom(fromEmail);
        sm.setTo(value.getEmail());
        sm.setSubject("token");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("token: " + value.getForgotPassToken());
        return sm;

    }



}
