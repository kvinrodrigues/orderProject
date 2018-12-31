package py.com.poraplz.cursomc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import py.com.poraplz.cursomc.entities.PagoConBoleto;
import py.com.poraplz.cursomc.entities.PagoConTarjeta;
import py.com.poraplz.cursomc.services.EmailService;
import py.com.poraplz.cursomc.services.MockEmailService;

@Configuration
public class AppConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagoConTarjeta.class);
                objectMapper.registerSubtypes(PagoConBoleto.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
