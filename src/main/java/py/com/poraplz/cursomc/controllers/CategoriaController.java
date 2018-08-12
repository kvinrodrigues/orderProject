package py.com.poraplz.cursomc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.poraplz.cursomc.dto.category.saveCategoryDto;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.repositories.CategoriaRepository;
import py.com.poraplz.cursomc.services.CategoriaService;
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

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET )
    public ResponseEntity<?> getCategory(@PathVariable Long id){
            Categoria categoria = service.getCategory(id);
            return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody saveCategoryDto dto) throws Exception {
        Categoria categoria = new Categoria();
        categoria.setName(dto.getName());
        if (categoria !=null)
            service.saveOrUpdate(categoria);

        return new ResponseEntity<>("Categoria creada exitosamente", HttpStatus.OK);
    }





}
