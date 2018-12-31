package py.com.poraplz.cursomc.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID= 1L;
    @JsonIgnore
    @EmbeddedId
    ItemPedidoPK id = new ItemPedidoPK();
    private Double discount;
    private Integer quantity;
    private Double price;

    public ItemPedido() {
    }

    public ItemPedido(Producto producto, Pedido pedido, Double discount, Integer quantity, Double price) {
        id.setProduct(producto);
        id.setOrder(pedido);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getSubtTotal() {
        return (price - discount) * quantity;

    }

    public void setOrder(Pedido pedido){
        id.setOrder(pedido);
    }

    public void setProduct(Producto product){
        id.setProduct(product);
    }
    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Producto getProducto(){
        return this.id.getProduct();
    }



    @JsonIgnore
    public Pedido getPedido(){
        return this.id.getOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat numberFormat= NumberFormat.getNumberInstance(new Locale("es","PY"));
        final StringBuilder sb = new StringBuilder("ItemPedido{");
        sb.append(getProducto().getName());
        sb.append(", Cantidad: ").append(getQuantity());
        sb.append(", Precio unitario:").append(numberFormat.format(getPrice()));
        sb.append(", SubTotal: ").append(numberFormat.format(getSubtTotal()));
        sb.append('}').append("\n");
        return sb.toString();
    }
}
