package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.poraplz.cursomc.dto.order.OrderDto;
import py.com.poraplz.cursomc.entities.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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

    public Pedido(Date moment, Pago pay, Direccion adress, Cliente client, Set<ItemPedido> items) {
        this.moment = moment;
        this.pay = pay;
        this.adress = adress;
        this.client = client;
        this.items = items;
    }

    /**
     * Constructor para carga de valores para la insercion de pedidos
     * @param dto: OrderDto
     */
    public Pedido(OrderDto dto){
        this.pay = dto.getPay();
        this.moment = new Date();
        this.items = dto.getItems();
        this.adress = dto.getAdress();
        this.client = dto.getClient();
        this.getPay().setAdress(adress);
        this.getPay().setEstado(EstadoPagamento.PENDIENTE);
        this.getPay().setOrder(this);

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
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es","PY"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("Pedido numero=").append(id);
        sb.append(", Instante: ").append(sdf.format(getMoment()));
        sb.append(", Cliente: ").append(getClient().getName());
        sb.append(", Estado de Pago: ").append(getPay().getEstado().getDescription());
        sb.append("\nDetalles:\n");
        for(ItemPedido ip : getItems()){
            sb.append(ip.toString());
        }
        sb.append("Valor total: ").append(nf.format(getTotalAmount()));

        sb.append('}');
        return sb.toString();
    }
}
