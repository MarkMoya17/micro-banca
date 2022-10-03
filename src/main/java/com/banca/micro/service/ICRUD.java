package com.banca.micro.service;

import java.util.List;

public interface ICRUD<T> {

    T crear(T t);
    T editar(T t);
    T leer(Long id);
    List<T> listar();
    void eliminar(Long id);

}
