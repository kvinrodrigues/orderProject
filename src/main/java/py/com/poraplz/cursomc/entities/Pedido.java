package py.com.poraplz.cursomc.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pedido implements Serializable{
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date moment;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Pago pay;

    private Direccion adress;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente client;

    public Pedido(){ }

    public Pedido(Date moment, Pago pay, Direccion adress, Cliente client) {
        this.moment = moment;
        this.pay = pay;
        this.adress = adress;
        this.client = client;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public Pago getPay() {
        return pay;
    }

    public void setPay(Pago pay) {
        this.pay = pay;
    }

    public Direccion getAdress() {
        return adress;
    }

    public void setAdress(Direccion adress) {
        this.adress = adress;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "moment=" + moment +
                ", pay=" + pay +
                ", adress=" + adress +
                ", client=" + client +
                '}';
    }
}
