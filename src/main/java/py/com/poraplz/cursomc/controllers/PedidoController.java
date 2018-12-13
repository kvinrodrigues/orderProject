package py.com.poraplz.cursomc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import py.com.poraplz.cursomc.dto.order.OrderDto;
import py.com.poraplz.cursomc.entities.Pedido;
import py.com.poraplz.cursomc.services.PedidoService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
    PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        Pedido pedido = service.findOrder(id);
        return ResponseEntity.ok(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody OrderDto request) {
        Pedido order = service.saveOrUpdate(service.fromDtoToOrder(request));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
