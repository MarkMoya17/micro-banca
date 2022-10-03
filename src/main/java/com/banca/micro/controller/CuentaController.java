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

import com.banca.micro.entity.CuentaEntity;
import com.banca.micro.service.ICuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final ICuentaService service;

    public CuentaController(ICuentaService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public CuentaEntity crear(@Valid @RequestBody CuentaEntity cuenta) {
        return service.crear(cuenta);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public CuentaEntity modificar(@Valid @RequestBody CuentaEntity cuenta) {
        return service.editar(cuenta);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<CuentaEntity> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CuentaEntity leer(@PathVariable Long id) {
        return service.leer(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }

}
