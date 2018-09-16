package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    @ManyToMany
    @JoinTable(name= "producto_categoria",
            joinColumns = @JoinColumn(name = "producto_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private List<Categoria> categories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<ItemPedido> items = new HashSet();


    public Producto() { }

    public Producto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> list = new ArrayList();
        for (ItemPedido x: items){
            list.add(x.getPedido());
        }
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Categoria> getCategorias() {
        return categories;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categories = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto productos = (Producto) o;
        return Objects.equals(id, productos.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Productos{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }
}
