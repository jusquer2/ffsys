package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.ProductosRepository;
import com.jusquer.ffsys.persistence.entity.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductosService {
    @Autowired
    ProductosRepository productosRepository;
    public List<Productos> getAll(){
        return productosRepository.findByEliminadoOrderByPrioridadAsc(false);
    }
    public Productos save(Productos productos){
        return productosRepository.save(productos);
    }
}
