package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.poraplz.cursomc.dto.client.ClientDTO;
import py.com.poraplz.cursomc.entities.enums.Perfil;
import py.com.poraplz.cursomc.entities.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable{
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String cpfOuCnpj;
    private Integer type;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Direccion> adresses = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name="TELEFONO")
    private Set<String> phone = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Pedido> orders = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "PERFILES")
    private Set<Integer> profile = new HashSet<>();

    public Cliente() {
        addProfile(Perfil.CLIENTE);
    }

    public Cliente(ClientDTO dto){
        Cliente client = new Cliente();
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setAdresses(dto.getAdresses());
    }


    public Cliente(String name, String email, String cpfOuCnpj, TipoCliente tipo, String pass) {
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.type = (tipo == null) ? null: tipo.getCod();
        this.password = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getType() {
        return TipoCliente.toEnum(this.type);
    }


    public void setTipo(TipoCliente tipo) {
        this.type = tipo.getCod();
    }


    public List<Direccion> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Direccion> adresses) {
        this.adresses = adresses;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }

    public List<Pedido> getOrders() {
        return orders;
    }

    public void setOrders(List<Pedido> orders) {
        this.orders = orders;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Perfil> getProfiles() {
        return profile.stream().map(value -> Perfil.toEnum(value)).collect(Collectors.toSet());
    }

    public void addProfile(Perfil profile) {
        this.profile.add(profile.getCod());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpfOuCnpj='" + cpfOuCnpj + '\'' +
                ", type=" + type +
                ", adresses=" + adresses +
                ", phone=" + phone +
                '}';
    }
}
