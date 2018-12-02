package py.com.poraplz.cursomc.dto.client;

import org.hibernate.validator.constraints.Length;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.Direccion;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientDTO {
    private Long id;
    @NotEmpty(message = "Campo obligatorio") @Length(min = 5, max = 120, message = "Longitud incorrecta")
    private String name;
    @NotEmpty(message = "Campo obligatorio") @Email
    private String email;

    private List<Direccion> adresses = new ArrayList<>();



    public ClientDTO() {
    }

    public ClientDTO(Cliente cliente){
        this.name = cliente.getName();
        this.email = cliente.getEmail();

    }

    public ClientDTO(String name, String email, String cpfOuCnpj, Integer type, List<Direccion> adresses, Set<String> phone) {
        this.name = name;
        this.email = email;

    }

    public List<Direccion> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Direccion> adresses) {
        this.adresses = adresses;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
