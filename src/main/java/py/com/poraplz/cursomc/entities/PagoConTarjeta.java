package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import py.com.poraplz.cursomc.entities.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
@JsonTypeName("PagoConTarjeta")
public class PagoConTarjeta extends Pago {

    private Integer plotsNumber;

    public PagoConTarjeta() { }

    public PagoConTarjeta(EstadoPagamento estado, Pedido pedido, Direccion direccion, Integer plotsNumber) {
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
