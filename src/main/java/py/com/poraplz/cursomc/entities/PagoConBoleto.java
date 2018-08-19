package py.com.poraplz.cursomc.entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagoConBoleto extends Pago {

    private Date expirationData;
    private Date payData;

    public PagoConBoleto() {
    }

    public PagoConBoleto(Integer estado, Pedido pedido, Direccion direccion, Date expirationData, Date payData) {
        super(estado, pedido, direccion);
        this.expirationData = expirationData;
        this.payData = payData;
    }

    public Date getExpirationData() {
        return expirationData;
    }

    public void setExpirationData(Date expirationData) {
        this.expirationData = expirationData;
    }

    public Date getPayData() {
        return payData;
    }

    public void setPayData(Date payData) {
        this.payData = payData;
    }
}
