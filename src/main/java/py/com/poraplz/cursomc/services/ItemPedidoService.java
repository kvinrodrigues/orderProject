package py.com.poraplz.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.ItemPedido;
import py.com.poraplz.cursomc.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository repository;

    public ItemPedido saveOrUpdate(ItemPedido itemPedido){
        return repository.save(itemPedido);
    }

}
