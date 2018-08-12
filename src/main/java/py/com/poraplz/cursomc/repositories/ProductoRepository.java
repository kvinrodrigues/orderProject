package py.com.poraplz.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.poraplz.cursomc.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    public Producto getById(Long id);
}
