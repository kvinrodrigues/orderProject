package py.com.poraplz.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Pago;
import py.com.poraplz.cursomc.repositories.PagoRepository;

@Service
public class PagoService {

   @Autowired
    private PagoRepository repository;

   public Pago saveOrUpdate(Pago pago){
       return repository.save(pago);

   }
}
