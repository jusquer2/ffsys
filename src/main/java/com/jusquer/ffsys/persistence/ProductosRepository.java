package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.persistence.crud.ProductosCrudRepository;
import com.jusquer.ffsys.persistence.entity.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductosRepository implements com.jusquer.ffsys.domain.repository.ProductosRepository {
    @Autowired
    ProductosCrudRepository productosCrudRepository;
    @Override
    public List<Productos> findByEliminadoOrderByPrioridadAsc(boolean eliminado) {
        return productosCrudRepository.findByEliminadoOrderByPrioridadAsc(false);
    }

    @Override
    public Productos save(Productos productos) {
        return productosCrudRepository.save(productos);
    }
}
