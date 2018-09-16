package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.poraplz.cursomc.entities.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pago implements Serializable{
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer payState;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Pedido order;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Direccion adress;

    public Pago() {
    }

    public Pago(EstadoPagamento payState, Pedido order, Direccion adress) {
        this.payState = payState.getValue();
        this.order = order;
        this.adress = adress;
    }


    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(this.payState);
    }

    public void setEstado(EstadoPagamento estado) {
        this.payState= estado.getValue();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return Objects.equals(id, pago.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


}
