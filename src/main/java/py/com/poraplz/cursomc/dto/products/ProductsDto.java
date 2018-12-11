package py.com.poraplz.cursomc.dto.products;

import py.com.poraplz.cursomc.entities.Producto;

public class ProductsDto {
    private Long id;
    private String name;
    private double price;

    public ProductsDto(Producto producto){
        this.name = producto.getName();
        this.price = producto.getPrice();
        this.id = producto.getId();
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
}
