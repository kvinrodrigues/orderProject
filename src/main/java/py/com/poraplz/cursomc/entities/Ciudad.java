package py.com.poraplz.cursomc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Ciudad implements Serializable{
    private static final long serialVersionUID=1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "state_id")
    private Estado state;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Direccion> adresses = new ArrayList<>();


    public Ciudad() {
    }

    public Ciudad(String name, Estado state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Estado getState() {
        return state;
    }

    public void setState(Estado state) {
        this.state = state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(id, ciudad.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
