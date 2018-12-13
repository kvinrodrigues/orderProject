package py.com.poraplz.cursomc.services;

import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.PagoConBoleto;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void processBoletoPayment(PagoConBoleto paymentInstance, Date startDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        paymentInstance.setExpirationData(calendar.getTime());
    }
}
