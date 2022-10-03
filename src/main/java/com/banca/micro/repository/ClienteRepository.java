package com.banca.micro.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banca.micro.entity.ClienteEntity;
import com.banca.micro.model.EstadoCuenta;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

    public static final String ESTADO_CUENTA_POR_CLIENTE = """
            SELECT m.fecha as fecha, 
            c.nombre as nombreCliente, 
            cu.nroCuenta as nroCuenta, 
            cu.tipoCuenta as tipoCuenta, 
            cu.saldoInicial as saldoInicial,
            cu.estado as estadoMovimiento, 
            m.valor as montoMovimiento, 
            m.saldo as saldoDisponible
            FROM ClienteEntity c
            INNER JOIN CuentaEntity cu
              ON c.id = cu.cliente.id
            INNER JOIN MovimientoEntity m
              ON cu.id = m.cuenta.id
            WHERE m.fecha between :fechaInicio and :fechaFin
              AND c.id = :idCliente
              ORDER BY m.fecha DESC
              """;

    @Query(value = ESTADO_CUENTA_POR_CLIENTE)
    public List<EstadoCuenta> obtenerEstadoCuenta(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("idCliente") Long idCliente);
    
}
