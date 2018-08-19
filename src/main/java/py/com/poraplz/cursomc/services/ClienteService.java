package py.com.poraplz.cursomc.services;

import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.ClienteRepository;

import java.util.Optional;


@Service
public class ClienteService {

    private ClienteRepository repo;

    public ClienteService(ClienteRepository clt){
        this.repo = clt;

    }

    public Cliente getClient(Long id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("No se encontro cliente, id:" + id ));


    }
}
