package py.com.poraplz.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.com.poraplz.cursomc.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

    public Estado getById(Long id);

}
