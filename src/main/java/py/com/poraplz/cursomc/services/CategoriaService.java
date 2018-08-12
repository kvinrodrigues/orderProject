package py.com.poraplz.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.CategoriaRepository;

import java.util.Optional;

@Service
public class CategoriaService {
    private static final Logger logger = LoggerFactory
            .getLogger(CategoriaService.class);

    CategoriaRepository repo;

    @Autowired
    public CategoriaService(CategoriaRepository repo){
        this.repo = repo;
    }

   public Categoria saveOrUpdate(Categoria categoria) throws Exception{
       try{
            return repo.save(categoria);
        }catch (Exception e){
            throw(e);
        }
   }

   public Categoria getCategory(Long id) {
       Optional<Categoria> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException("No se encontro categoria, id:" + id ));
   }
}
