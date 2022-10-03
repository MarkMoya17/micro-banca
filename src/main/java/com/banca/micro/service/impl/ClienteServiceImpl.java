package com.banca.micro.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banca.micro.entity.ClienteEntity;
import com.banca.micro.exception.ModeloNotFoundException;
import com.banca.micro.model.EstadoCuenta;
import com.banca.micro.repository.ClienteRepository;
import com.banca.micro.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteEntity crear(ClienteEntity t) {
        return repository.save(t);
    }

    @Override
    public ClienteEntity editar(ClienteEntity t) {
        findClientValidById(t.getId());
        return repository.save(t);
    }

    @Override
    public ClienteEntity leer(Long id) {
        Optional<ClienteEntity> cliente = findClientValidById(id);
        return cliente.get();
    }

    @Override
    public List<ClienteEntity> listar() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        Optional<ClienteEntity> cliente = findClientValidById(id);
        repository.delete(cliente.get());
    }

    private Optional<ClienteEntity> findClientValidById(Long id) {
        Optional<ClienteEntity> cliente = repository.findById(id);
        if (cliente.isEmpty())
            throw new ModeloNotFoundException("Not Found id: " + id);
        return cliente;
    }

    @Override
    public List<EstadoCuenta> estadoCuenta(Long idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        
        return repository.obtenerEstadoCuenta(fechaInicio, fechaFin, idCliente);
    }
}
