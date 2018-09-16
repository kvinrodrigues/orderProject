package py.com.poraplz.cursomc.services;

import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Pedido;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.PedidoRepository;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository dao;


    public PedidoService(PedidoRepository repo){
        this.dao = repo;
    }

    public Pedido findOrder(Long id){
        Optional<Pedido> obj = dao.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("No se encontro pedido "+
                ", Tipo: " + Pedido.class.getName()));
    }
}
