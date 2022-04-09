package com.jusquer.ffsys.persistence.crud;

import com.jusquer.ffsys.persistence.entity.Productos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductosCrudRepository extends CrudRepository<Productos,Integer> {
    List<Productos> findByEliminadoOrderByPrioridadAsc(boolean eliminado);
}
