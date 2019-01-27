package py.com.poraplz.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.Pedido;

public interface EmailService {
    void sendOrderConfirmation(Pedido order);
    void sendEmail(SimpleMailMessage msg);
    void sendChangeOfPassword(String token);
    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
