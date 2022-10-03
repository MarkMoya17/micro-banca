package com.banca.micro.model;

import java.time.LocalDateTime;

public interface EstadoCuenta {

    LocalDateTime getFecha();
    String getNombreCliente();
    String getNroCuenta();
    String getTipoCuenta();
    Double getSaldoInicial();
    Boolean getEstadoMovimiento();
    Double getMontoMovimiento();
    Double getSaldoDisponible();

}
