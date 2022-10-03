package com.banca.micro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banca.micro.entity.CuentaEntity;
import com.banca.micro.entity.MovimientoEntity;
import com.banca.micro.exception.ModeloNotFoundException;
import com.banca.micro.repository.CuentaRepository;
import com.banca.micro.repository.MovimientoRepository;
import com.banca.micro.service.IMovimientoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovimientoServiceImpl implements IMovimientoService {

    private static final String DEPOSITO = "DEPOSITO";
    private static final String RETIRO = "RETIRO";

    private final MovimientoRepository repository;
    private final CuentaRepository repositoryCuenta;

    public MovimientoServiceImpl(MovimientoRepository repository, CuentaRepository repositoryCuenta) {
        this.repository = repository;
        this.repositoryCuenta = repositoryCuenta;
    }

    @Override
    public MovimientoEntity crear(MovimientoEntity movimiento) {
        if (!esTipoDeMovimientoValido(movimiento))
            throw new ModeloNotFoundException("Tipo de movimiento no válido. Ingrese Deposito o Retiro");

        Optional<CuentaEntity> cuenta = repositoryCuenta.findFirstByEstadoAndNroCuenta(true,
                movimiento.getCuenta().getNroCuenta());
        if (cuenta.isEmpty())
            throw new ModeloNotFoundException("No se encontró la cuenta");

        Double valor = obtenerValorxTipoMovimiento(movimiento, cuenta);
        if (valor == null) {
            throw new ModeloNotFoundException("No se puede realizar el movimiento");
        }

        log.info("valor {}", valor);
        Double nuevoSaldo = obtenerSaldoDisponible(cuenta.get()) + valor;
        movimiento.setValor(valor);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta.get());

        return repository.save(movimiento);
    }

    private boolean esTipoDeMovimientoValido(MovimientoEntity movimiento) {
        if (RETIRO.equalsIgnoreCase(movimiento.getTipoMovimiento())
                || DEPOSITO.equalsIgnoreCase(movimiento.getTipoMovimiento()))
            return true;
        return false;
    }

    /**
     * @param movimiento
     * @param cuenta
     */
    private Double obtenerValorxTipoMovimiento(MovimientoEntity movimiento, Optional<CuentaEntity> cuenta) {
        if (RETIRO.equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            if (obtenerSaldoDisponible(cuenta.get()) == 0)
                throw new ModeloNotFoundException("Saldo no disponible");

            if (movimiento.getValor() > 0)
                return movimiento.getValor() * -1;
        }
        if (DEPOSITO.equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            if (movimiento.getValor() < 0)
                return movimiento.getValor() * -1;
        }
        return movimiento.getValor();
    }

    /**
     * @param cuenta
     */
    private Double obtenerSaldoDisponible(CuentaEntity cuenta) {
        Optional<MovimientoEntity> ultimoMovimiento = repository.findFirstByCuentaOrderByFechaDesc(cuenta);
        if (ultimoMovimiento.isPresent())
            return ultimoMovimiento.get().getSaldo();

        return cuenta.getSaldoInicial();
    }

    @Override
    public MovimientoEntity editar(MovimientoEntity movimiento) {
        MovimientoEntity movimientoEntity = findMovimientoValidById(movimiento.getId());

        if (!esTipoDeMovimientoValido(movimiento))
            throw new ModeloNotFoundException("Tipo de movimiento no válido. Ingrese Deposito o Retiro");

        Optional<CuentaEntity> cuenta = repositoryCuenta.findFirstByEstadoAndNroCuenta(true,
                movimiento.getCuenta().getNroCuenta());
        if (cuenta.isEmpty())
            throw new ModeloNotFoundException("No se encontró la cuenta");

        Double valor = obtenerValorxTipoMovimiento(movimiento, cuenta);
        if (valor == null) {
            throw new ModeloNotFoundException("No se puede realizar el movimiento");
        }

        log.info("valor {}", valor);
        movimientoEntity.setCuenta(cuenta.get());
        movimientoEntity.setFecha(movimiento.getFecha());
        movimientoEntity.setSaldo(movimiento.getSaldo());
        movimientoEntity.setValor(valor);

        return repository.save(movimientoEntity);
    }

    @Override
    public MovimientoEntity leer(Long id) {
        return findMovimientoValidById(id);
    }

    @Override
    public List<MovimientoEntity> listar() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        MovimientoEntity movimiento = findMovimientoValidById(id);
        repository.delete(movimiento);
    }

    private MovimientoEntity findMovimientoValidById(Long id) {
        Optional<MovimientoEntity> movimiento = repository.findById(id);
        if (movimiento.isEmpty())
            throw new ModeloNotFoundException("Not Found id: " + id);
        return movimiento.get();
    }

}
