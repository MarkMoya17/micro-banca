package com.banca.micro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.micro.entity.CuentaEntity;
import com.banca.micro.entity.MovimientoEntity;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    
    public Optional<MovimientoEntity> findFirstByCuentaOrderByFechaDesc(CuentaEntity cuenta);

}
