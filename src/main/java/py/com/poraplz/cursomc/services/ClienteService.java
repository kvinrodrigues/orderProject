package py.com.poraplz.cursomc.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.dto.client.ClientDTO;
import py.com.poraplz.cursomc.dto.client.ClientsDTO;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.exceptions.DataIntegrityException;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public Cliente saveOrUpdate(Cliente cliente){
        return repo.save(cliente);

    }

    public Cliente updateClient(Long id, ClientDTO request){
      return saveOrUpdate(setDataToUpdate(id, request));

    }

    public Cliente saveClient(ClientDTO request){
        return saveOrUpdate(fromDtoToClient(request));

    }

    public Page<Cliente> filterCliente(Integer page, Integer linesPerPage,String direction, String column){

        System.out.println("DIRECTION: "+ direction);
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,
                Sort.Direction.valueOf(direction), column);
        return repo.findAll(pageRequest);
    }

    public void deleteClient(Long id){
        getClient(id);
        try{
            repo.deleteById(id);

        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Ya existen datos asociadas al cliente");
        }
    }

    public List<ClientsDTO> getAllClients(){
        List<Cliente> clients= repo.findAll();
        List<ClientsDTO> clientsDTOS = clients
                .stream()
                .map(obj -> new ClientsDTO(obj))
                .collect(Collectors.toList());
        return clientsDTOS;

    }

    public Cliente setDataToUpdate(Long id, ClientDTO dto){
        Cliente client = getClient(id);
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        return client;

    }

    public Cliente fromDtoToClient(ClientDTO dto){
        return new Cliente(dto);

    }

    public ClientDTO toDTO(Cliente cliente){
        return  new ClientDTO(cliente);
    }


}
