package py.com.poraplz.cursomc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.repositories.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private static final Logger logger = LoggerFactory
            .getLogger(CategoriaController.class);



    CategoriaRepository repo;
    public CategoriaController(CategoriaRepository repo){
        this.repo = repo;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listar(){
        List<Categoria> categorias = repo.findAll();
        return  ResponseEntity.ok().body(categorias);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id){
        Categoria categoria = repo.getById(id);
        return ResponseEntity.ok().body(categoria);
    }





}
