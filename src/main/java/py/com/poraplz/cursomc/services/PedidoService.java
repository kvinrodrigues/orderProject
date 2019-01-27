package py.com.poraplz.cursomc.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.dto.order.OrderDto;
import py.com.poraplz.cursomc.entities.*;
import py.com.poraplz.cursomc.exceptions.AuthorizationException;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.PedidoRepository;
import py.com.poraplz.cursomc.security.User;

import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository dao;
    private BoletoService boletoService;
    private PagoService pagoService;
    private ProductoService productoService;
    private ItemPedidoService itemPedidoService;
    private ClienteService clienteService;
    private EmailService emailService;
    private UserService userService;

    public PedidoService(PedidoRepository repo, BoletoService boletoService, PagoService pagoService,
                         ProductoService productoService, ItemPedidoService itemPedidoService,
                         ClienteService clienteService, EmailService emailService){
        this.dao = repo;
        this.boletoService = boletoService;
        this.pagoService = pagoService;
        this.productoService = productoService;
        this.itemPedidoService = itemPedidoService;
        this.clienteService = clienteService;
        this.emailService = emailService;
    }

    public Pedido findOrder(Long id){
        Optional<Pedido> obj = dao.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("No se encontro pedido "+
                ", Tipo: " + Pedido.class.getName()));
    }

    public Page<Pedido> ByClient(Integer page, Integer linesPerPage, String column
            , String direction){
        User user = UserService.getLoggedUser();
        if(user == null) {
            throw new AuthorizationException("Acceso denegado");
        }
        Cliente cliente = clienteService.getClient(user.getId());
        PageRequest pageRequest = PageRequest.of(page, linesPerPage,
                Sort.Direction.valueOf(direction), column);
        return dao.findByClient(cliente, pageRequest);

    }

    public Pedido saveOrUpdate(Pedido order){
        if(order.getPay() instanceof PagoConBoleto){
            PagoConBoleto pagoConBoleto = (PagoConBoleto) order.getPay();
            boletoService.processBoletoPayment(pagoConBoleto, order.getMoment());
        }else if(order.getPay() instanceof PagoConTarjeta){
            PagoConTarjeta pagoConTarjeta = (PagoConTarjeta) order.getPay();

        }
        Cliente client = clienteService.getClient(order.getClient().getId());
        order.setClient(client);
        order = dao.save(order);
        pagoService.saveOrUpdate(order.getPay());

        for(ItemPedido item: order.getItems()){
            item.setDiscount(0.0D);
            Producto producto = productoService.findById(item.getProducto().getId());
            item.setProduct(producto);
            item.setPrice(producto.getPrice());
            item.setOrder(order);

            itemPedidoService.saveOrUpdate(item);
        }
        emailService.sendOrderConfirmation(order);
        return order;
    }

    public Pedido fromDtoToOrder(OrderDto dto){

        return new Pedido(dto);
    }
}
