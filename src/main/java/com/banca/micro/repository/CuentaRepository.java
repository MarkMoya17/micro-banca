package com.banca.micro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.micro.entity.CuentaEntity;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long>{

    public Optional<CuentaEntity> findFirstByEstadoAndNroCuenta(boolean estado, String nroCuenta);

}
