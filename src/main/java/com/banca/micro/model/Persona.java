package com.banca.micro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@MappedSuperclass
public class Persona implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long idPersona;
    
    @Column(nullable = false)
    private String nombre;
    
    private String genero;
    
    private Integer edad;
    
    @Column(nullable = false)
    private String identificacion;
    
    private String direccion;
    
    private String telefono;

}
