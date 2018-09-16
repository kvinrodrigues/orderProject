package py.com.poraplz.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.poraplz.cursomc.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}
