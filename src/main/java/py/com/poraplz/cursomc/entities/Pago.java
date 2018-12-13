package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import py.com.poraplz.cursomc.entities.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pago implements Serializable{
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
        this.payState = (payState == null) ? null: payState.getValue();
        this.order = order;
        this.adress = adress;
    }



    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(this.payState);
    }

    public void setEstado(EstadoPagamento estado) {
        this.payState= estado.getValue();
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Pedido getOrder() {
        return order;
    }

    public void setOrder(Pedido order) {
        this.order = order;
    }

    public Direccion getAdress() {
        return adress;
    }

    public void setAdress(Direccion adress) {
        this.adress = adress;
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
