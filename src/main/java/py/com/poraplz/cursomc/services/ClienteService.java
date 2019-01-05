package py.com.poraplz.cursomc.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.poraplz.cursomc.dto.client.ClientDTO;
import py.com.poraplz.cursomc.dto.client.ClientNewDTO;
import py.com.poraplz.cursomc.dto.client.ClientsDTO;
import py.com.poraplz.cursomc.entities.Ciudad;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.Direccion;
import py.com.poraplz.cursomc.entities.enums.TipoCliente;
import py.com.poraplz.cursomc.exceptions.DataIntegrityException;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.CiudadRepository;
import py.com.poraplz.cursomc.repositories.ClienteRepository;
import py.com.poraplz.cursomc.repositories.DireccionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClienteService {

    private ClienteRepository repo;
    private DireccionRepository direccionRepository;
    private CiudadRepository ciudadRepository;
    private BCryptPasswordEncoder encoder;

    public ClienteService(ClienteRepository clt, DireccionRepository direccionRepository, CiudadRepository ciudadRepository,
    BCryptPasswordEncoder encoder){
        this.repo = clt;
        this.direccionRepository = direccionRepository;
        this.ciudadRepository = ciudadRepository;
        this.encoder = encoder;

    }

    public Cliente getClient(Long id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("No se encontro cliente, id:" + id ));

    }

    public Cliente getClientByEmail(String email){
        return repo.getByEmail(email);
    }

    public Cliente saveOrUpdate(Cliente cliente){
        cliente = repo.save(cliente);
        direccionRepository.saveAll(cliente.getAdresses());
        return cliente;

    }

    public Cliente updateClient(Long id, ClientDTO request){
      return saveOrUpdate(setDataToUpdate(id, request));

    }

    @Transactional
    public Cliente saveClient(ClientNewDTO request){
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
        return clients.stream()
                .map(obj -> new ClientsDTO(obj))
                .collect(Collectors.toList());


    }

    public Cliente setDataToUpdate(Long id, ClientDTO dto){
        Cliente client = getClient(id);
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        return client;

    }


    public Cliente fromDtoToClient(ClientNewDTO dto){
        Cliente cliente = new Cliente(dto.getName(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getType()), encoder.encode(dto.getPass()));
        Ciudad ciudad = ciudadRepository.findById(dto.getCityId()).orElseThrow(() ->
                new ObjectNotFoundException("No se encontro ciudad, id: "+ dto.getCityId()));
        Direccion dir = new Direccion(dto.getStreet(), dto.getNumber(), dto.getComplement(), dto.getDistrict(), ciudad, cliente);
        cliente.getAdresses().add(dir);
        cliente.getPhone().add(dto.getFirstPhone());
        if(dto.getSecondPhone() != null)
            cliente.getPhone().add(dto.getSecondPhone());
        if(dto.getThirdPhone() != null)
            cliente.getPhone().add(dto.getThirdPhone());
        return cliente;

    }


}
