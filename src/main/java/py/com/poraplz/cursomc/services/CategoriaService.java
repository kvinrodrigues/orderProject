package py.com.poraplz.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.dto.category.CategoriesDto;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.exceptions.DataIntegrityException;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private static final Logger logger = LoggerFactory
            .getLogger(CategoriaService.class);

    private  CategoriaRepository repo;

    @Autowired
    public CategoriaService(CategoriaRepository repo){
        this.repo = repo;
    }

   public Categoria saveOrUpdate(Categoria categoria){
       try{
            return repo.save(categoria);
        }catch (Exception e){
            throw(e);
        }
   }

   public Categoria findCategory(Long id) {
       Optional<Categoria> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException("No se encontro categoria, id:" + id ));
   }

   public Categoria updateCategory(CategoriesDto request, Long id){
            Categoria categoria = findCategory(id);
            return saveOrUpdate(setDataToUpdate(categoria, request));
    }

    public Categoria setDataToUpdate(Categoria actual, CategoriesDto newValue){
        actual.setName(newValue.getName());
        actual.setProducts(newValue.getProducts());
        return actual;
    }

    public void deleteCategory(Long id) {
        findCategory(id);
        try {
            repo.deleteById(id);

        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Ya existen productos con la categoria existente");
        }
    }

    public List<CategoriesDto> getAllCategories(){
        List<Categoria> categories = repo.findAll();
        List<CategoriesDto> allCat = categories
                .stream()
                .map(obj -> new CategoriesDto(obj))
                .collect(Collectors.toList());
        return allCat;
    }

    public Page<Categoria> filterCategory(Integer page, Integer linesPerPage, String column
            , String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage,
                Sort.Direction.valueOf(direction), column);
        return repo.findAll(pageRequest);

    }

    public Categoria fromDtoToCategory(CategoriesDto dto){
        return new Categoria(dto.getName());
    }
}
