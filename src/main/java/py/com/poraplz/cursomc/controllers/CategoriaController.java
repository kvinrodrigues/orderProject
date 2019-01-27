package py.com.poraplz.cursomc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import py.com.poraplz.cursomc.dto.category.CategoriesDto;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.repositories.CategoriaRepository;
import py.com.poraplz.cursomc.services.CategoriaService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private static final Logger logger = LoggerFactory
            .getLogger(CategoriaController.class);
    private CategoriaRepository repo;
    private CategoriaService service;

    public CategoriaController(CategoriaRepository repo, CategoriaService service){
        this.repo = repo;
        this.service = service;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> listar(){
        try {
            List<Categoria> categorias = repo.findAll();
            return ResponseEntity.ok().body(categorias);
        }catch (Exception e){
            logger.error("Al listar categorias");
            logger.debug("Al listar categorias-->" + e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public ResponseEntity<?> findCategoryById(@PathVariable Long id){
            Categoria categoria = service.findCategory(id);
            return ResponseEntity.ok().body(categoria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriesDto request){
        Categoria categoria = service.saveOrUpdate(service.fromDtoToCategory(request));
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(categoria.getId())
                    .toUri();
        return ResponseEntity.created(uri).build();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriesDto request, @PathVariable Long id){
        Categoria resp = service.updateCategory(request, id);
        return ResponseEntity.ok().body(resp);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDto>> findAll(){
        return ResponseEntity.ok().body(service.getAllCategories());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriesDto>> categoryPage(
                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                        @RequestParam(value = "orderBy", defaultValue = "name") String columnName){
        Page<Categoria> categorias = service.filterCategory(page, linesPerPage,columnName , direction);
        Page<CategoriesDto> categoriesDtos = categorias
                .map(obj -> new CategoriesDto(obj));

        return ResponseEntity.ok().body(categoriesDtos);
    }













}
