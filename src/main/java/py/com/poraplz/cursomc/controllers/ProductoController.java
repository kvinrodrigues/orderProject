package py.com.poraplz.cursomc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.com.poraplz.cursomc.dto.products.ProductsDto;
import py.com.poraplz.cursomc.entities.Producto;
import py.com.poraplz.cursomc.services.ProductoService;
import py.com.poraplz.cursomc.utils.URLUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/producto")
public class ProductoController {
    @Autowired
    private ProductoService service;

    //TODO validar categories vacio
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductsDto>> categoryPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String ids,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String columnName){

            List<Long> categoriesIds = Arrays.asList(ids.split(",")).stream().map(val -> Long.parseLong(val)).collect(Collectors.toList());
            Page<Producto> productos = service.search(URLUtils.decodeParam(name), categoriesIds, page, linesPerPage,columnName , direction);
            Page<ProductsDto> dto = productos.map(value -> new ProductsDto(value));
            return ResponseEntity.ok().body(dto);

    }
}
