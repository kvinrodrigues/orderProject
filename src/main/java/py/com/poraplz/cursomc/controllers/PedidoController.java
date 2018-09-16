package py.com.poraplz.cursomc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.poraplz.cursomc.entities.Pedido;
import py.com.poraplz.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
    PedidoService pedidoService;

    public PedidoController(PedidoService service){
        this.pedidoService = service;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        Pedido pedido = pedidoService.findOrder(id);
        return ResponseEntity.ok(pedido);
    }
}
