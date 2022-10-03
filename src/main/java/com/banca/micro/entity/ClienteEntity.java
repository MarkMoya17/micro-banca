package com.banca.micro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.banca.micro.model.Persona;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Cliente")
@EqualsAndHashCode(callSuper = false)
public class ClienteEntity extends Persona implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Length(max = 16)
    private String contrasenia;
    
    @Column(nullable = false)
    private Boolean estado;
    
    @Builder
    public ClienteEntity(Long idPersona, String nombre, String genero, 
            Integer edad, String identificacion, String direccion, String telefono,
            Long id, String contrasenia, Boolean estado) {
        super(idPersona, nombre, genero, edad, identificacion, direccion, telefono);
        this.id = id;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }
}
