package py.com.poraplz.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.dto.category.saveACategoryDto;
import py.com.poraplz.cursomc.entities.Categoria;
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

   public Categoria saveOrUpdate(saveACategoryDto dto) throws Exception{
       Categoria categoria = new Categoria();
       try{
            categoria.setName(dto.getName());
            return repo.save(categoria);
        }catch (Exception e){
            throw(e);
        }
   }

   public Categoria find(Long id){
       Optional<Categoria> obj = repo.findById(id);
       return obj.orElse(null);

   }



}
