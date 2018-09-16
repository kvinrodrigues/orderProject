package py.com.poraplz.cursomc.entities;

import py.com.poraplz.cursomc.entities.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagoConBoleto extends Pago {

    private Date expirationData;
    private Date payData;

    public PagoConBoleto() {
    }

    public PagoConBoleto(EstadoPagamento estado, Pedido pedido, Direccion direccion, Date expirationData, Date payData) {
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
