package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Direccion implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Barrio
    private String street;
    private Integer number;
    private String complement;
    //Barrio
    private String district;
    private String cep;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id")
    private Cliente client;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private Ciudad city;

    public Direccion() {
    }

    public Direccion(String street, Integer number, String complement, String district, Ciudad ciudad, Cliente cliente) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = ciudad;
        this.client = cliente;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCiudad(Ciudad ciudad) {
        this.city = ciudad;
    }

    public Ciudad getCity() {
        return city;
    }

    public void setCity(Ciudad city) {
        this.city = city;
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
        Direccion direccion = (Direccion) o;
        return Objects.equals(id, direccion.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                ", district='" + district + '\'' +
                ", cep='" + cep + '\'' +
                ", client=" + client +
                ", city=" + city +
                '}';
    }
}
