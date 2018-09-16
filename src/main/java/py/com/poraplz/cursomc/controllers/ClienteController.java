package py.com.poraplz.cursomc.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service){
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findClienteById(@PathVariable Long id){
        Cliente cliente = service.getClient(id);
        return ResponseEntity.ok().body(cliente);

    }

}
