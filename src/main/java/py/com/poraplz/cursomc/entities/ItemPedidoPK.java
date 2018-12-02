package py.com.poraplz.cursomc.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPK implements Serializable{
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Pedido order;
    @ManyToOne
    @JoinColumn(name= "product_id")
    private Producto product;

    public ItemPedidoPK() {
    }

    public ItemPedidoPK(Pedido order, Producto product) {
        this.order = order;
        this.product = product;
    }

    public Pedido getOrder() {
        return order;
    }

    public void setOrder(Pedido order) {
        this.order = order;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);

    }
}
