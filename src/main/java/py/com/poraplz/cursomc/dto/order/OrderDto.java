package py.com.poraplz.cursomc.dto.order;

import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.entities.Direccion;
import py.com.poraplz.cursomc.entities.ItemPedido;
import py.com.poraplz.cursomc.entities.Pago;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {

    private Pago pay;
    private Direccion adress;
    private Cliente client;
    private Set<ItemPedido> items = new HashSet();
    public Pago getPay() {
        return pay;
    }

    public Direccion getAdress() {
        return adress;
    }

    public Cliente getClient() {
        return client;
    }

    public Set<ItemPedido> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "pay=" + pay +
                ", adress=" + adress +
                ", client=" + client +
                ", items=" + items +
                '}';
    }
}
