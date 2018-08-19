package py.com.poraplz.cursomc.entities;

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
    private Integer state;
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Pedido order;

    private Direccion adress;

    public Pago() {
    }

    public Pago(Integer estado, Pedido pedido, Direccion direccion) {
        this.state = estado;
        this.order = pedido;
        this.adress = direccion;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(this.state);
    }

    public void setEstado(EstadoPagamento estado) {
        this.state= estado.getValue();
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

    @Override
    public String toString() {
        return "Pago{" +
                "estado=" + state +
                ", pedido=" + order +
                ", direccion=" + adress +
                '}';
    }
}
