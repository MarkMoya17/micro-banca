package com.banca.micro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banca.micro.entity.CuentaEntity;
import com.banca.micro.exception.ModeloNotFoundException;
import com.banca.micro.repository.CuentaRepository;
import com.banca.micro.service.ICuentaService;

@Service
public class CuentaServiceImpl implements ICuentaService {

    private final CuentaRepository repository;

    public CuentaServiceImpl(CuentaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CuentaEntity crear(CuentaEntity t) {
        return repository.save(t);
    }

    @Override
    public CuentaEntity editar(CuentaEntity t) {
        findCuentaValidById(t.getId());
        return repository.save(t);
    }

    @Override
    public CuentaEntity leer(Long id) {
        Optional<CuentaEntity> cuenta = findCuentaValidById(id);
        return cuenta.get();
    }

    @Override
    public List<CuentaEntity> listar() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        Optional<CuentaEntity> cuenta = findCuentaValidById(id);
        repository.delete(cuenta.get());
    }

    private Optional<CuentaEntity> findCuentaValidById(Long id) {
        Optional<CuentaEntity> cuenta = repository.findById(id);
        if (cuenta.isEmpty())
            throw new ModeloNotFoundException("Not Found id: " + id);
        return cuenta;
    }

}
