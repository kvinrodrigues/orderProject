package py.com.poraplz.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Transactional(readOnly = true)
    Page<Pedido> findByClient(Cliente cliente, Pageable pageRequest);

}
