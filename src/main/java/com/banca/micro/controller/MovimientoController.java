package com.banca.micro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banca.micro.entity.MovimientoEntity;
import com.banca.micro.service.IMovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final IMovimientoService service;
    
    public MovimientoController(IMovimientoService service) {
        this.service = service;
    }
    
    @PostMapping(produces = "application/json", consumes = "application/json")
    public MovimientoEntity crear(@Valid @RequestBody MovimientoEntity movimiento) {
        return service.crear(movimiento);
    }
    
    @PutMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public MovimientoEntity modificar(@Valid @RequestBody MovimientoEntity movimiento) {
        return service.editar(movimiento);
    }
    
    @GetMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<MovimientoEntity> listar() {
        return service.listar();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MovimientoEntity leer(@PathVariable Long id){
        return service.leer(id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }
    
}
