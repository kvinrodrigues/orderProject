package py.com.poraplz.cursomc.controllers;


import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import py.com.poraplz.cursomc.dto.client.ClientDTO;
import py.com.poraplz.cursomc.dto.client.ClientNewDTO;
import py.com.poraplz.cursomc.dto.client.ClientsDTO;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.services.ClienteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service){
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findClienteById(@PathVariable Long id){
        Cliente cliente = service.getClient(id);
        return ResponseEntity.ok().body(cliente);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO request, @PathVariable Long id){
        Cliente cliente = service.updateClient(id, request);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody ClientNewDTO request){
        Cliente cliente = service.saveClient(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClientsDTO>> filterClientPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                               @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                               @RequestParam(value = "orderBy", defaultValue = "name") String columnName){

        Page<Cliente> clients = service.filterCliente(page, linesPerPage, direction, columnName);
        Page<ClientsDTO> clientsDto = clients.map(obj -> new ClientsDTO(obj));
        return ResponseEntity.ok().body(clientsDto);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable  Long id){
        service.deleteClient(id);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientsDTO>> all(){
        return ResponseEntity.ok().body(service.getAllClients());

    }




}
