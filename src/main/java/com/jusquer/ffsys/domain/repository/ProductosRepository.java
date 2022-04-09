package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.persistence.entity.Productos;

import java.util.List;

public interface ProductosRepository {
    List<Productos> findByEliminadoOrderByPrioridadAsc(boolean eliminado);
    Productos save(Productos productos);
}
