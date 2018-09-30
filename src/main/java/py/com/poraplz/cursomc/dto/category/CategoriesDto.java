package py.com.poraplz.cursomc.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.entities.Producto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDto {
    @JsonIgnore
    private Long id;
    @NotEmpty(message = "0031") @Size(min = 5, max = 80, message = "0032")
    private String name;
    @JsonIgnore
    public List<Producto> products = new ArrayList<>();


    public CategoriesDto() {
    }

    public CategoriesDto(Categoria categoria){
        this.id = categoria.getId();
        this.name = categoria.getName();
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

    public List<Producto> getProducts() {
        return products;
    }

    public void setProducts(List<Producto> products) {
        this.products = products;
    }
}
