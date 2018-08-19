package py.com.poraplz.cursomc.entities;

import javax.persistence.Entity;

@Entity
public class PagoConTarjeta extends Pago {

    private Integer plotsNumber;

    public PagoConTarjeta() { }

    public PagoConTarjeta(Integer estado, Pedido pedido, Direccion direccion, Integer plotsNumber) {
        super(estado, pedido, direccion);
        this.plotsNumber = plotsNumber;
    }

    public Integer getPlotsNumber() {
        return plotsNumber;
    }

    public void setPlotsNumber(Integer plotsNumber) {
        this.plotsNumber = plotsNumber;
    }
}
