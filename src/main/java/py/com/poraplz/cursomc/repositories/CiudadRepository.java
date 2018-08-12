package py.com.poraplz.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.com.poraplz.cursomc.entities.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long>{

    public Ciudad getById(Long id);
}
