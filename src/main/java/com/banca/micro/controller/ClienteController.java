package com.banca.micro.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banca.micro.entity.ClienteEntity;
import com.banca.micro.model.EstadoCuenta;
import com.banca.micro.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService service;

    public ClienteController(IClienteService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ClienteEntity crear(@Valid @RequestBody ClienteEntity cliente) {
        return service.crear(cliente);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public ClienteEntity modificar(@Valid @RequestBody ClienteEntity cliente) {
        return service.editar(cliente);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ClienteEntity> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ClienteEntity leer(@PathVariable Long id) {
        return service.leer(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }

    @GetMapping("/estadoCuenta")
    @ResponseStatus(code = HttpStatus.OK)
    public List<EstadoCuenta> estadoCuenta(@RequestParam("idCliente") Long idCliente,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        LocalDateTime dtfechaInicio = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime dtfechaFin = LocalDateTime.parse(fechaFin, formatter);
        return service.estadoCuenta(idCliente, dtfechaInicio, dtfechaFin);
    }
    
}
