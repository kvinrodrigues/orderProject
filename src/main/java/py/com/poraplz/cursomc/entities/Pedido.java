package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Pedido implements Serializable{
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date moment;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Pago pay;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Direccion adress;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente client;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemPedido> items = new HashSet();

    public Pedido(){ }

    public Pedido(Date moment, Direccion adress, Cliente client) {
        this.moment = moment;
        this.adress = adress;
        this.client = client;
    }

    public Pedido(Date moment, Pago pay, Direccion adress, Cliente client) {
        this.moment = moment;
        this.pay = pay;
        this.adress = adress;
        this.client = client;
    }

    public Double getTotalAmount(){
        Double sum = 0.0D;
        for(ItemPedido item:items){
            sum += item.getSubtTotal();
        }
        return sum;
    }

    @JsonIgnore
    public List<Producto> getProductos(){
        List<Producto> list = new ArrayList();
        for (ItemPedido x: items){
            list.add(x.getProducto());
        }
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public Set<ItemPedido> getItems() {
        return items;
    }

    public void setItems(Set<ItemPedido> items) {
        this.items = items;
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
