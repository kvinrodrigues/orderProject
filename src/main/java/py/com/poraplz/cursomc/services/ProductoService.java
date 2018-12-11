package py.com.poraplz.cursomc.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.entities.Producto;
import py.com.poraplz.cursomc.exceptions.ObjectNotFoundException;
import py.com.poraplz.cursomc.repositories.CategoriaRepository;
import py.com.poraplz.cursomc.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoService {
    private ProductoRepository repo;
    private CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository repo, CategoriaRepository categoriaRepository){
        this.repo = repo;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto findById(Long id){
        Optional<Producto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Producto no encontrado, id: "+ id));
    }

    public Page<Producto> search(String name, List<Long> ids, Integer page, Integer linesPerPage,
                                 String column, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), column);
        List<Categoria> categories = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }
}
