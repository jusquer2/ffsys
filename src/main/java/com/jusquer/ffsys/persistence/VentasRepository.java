package com.jusquer.ffsys.persistence;


import com.jusquer.ffsys.persistence.crud.VentaCrudRepository;
import com.jusquer.ffsys.persistence.entity.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VentasRepository implements com.jusquer.ffsys.domain.repository.VentasRepository {
    @Autowired
    VentaCrudRepository ventaCrudRepository;
    @Override
    public Ventas save(Ventas ventas) {
        return  ventaCrudRepository.save(ventas);
    }
}
