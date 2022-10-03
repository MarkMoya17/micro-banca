package com.banca.micro.service;

import java.time.LocalDateTime;
import java.util.List;

import com.banca.micro.entity.ClienteEntity;
import com.banca.micro.model.EstadoCuenta;

public interface IClienteService extends ICRUD<ClienteEntity>{

    List<EstadoCuenta> estadoCuenta(Long idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
}
