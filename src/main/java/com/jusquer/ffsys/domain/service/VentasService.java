package com.jusquer.ffsys.domain.service;


import com.jusquer.ffsys.domain.repository.VentasRepository;
import com.jusquer.ffsys.persistence.entity.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentasService {
    @Autowired
    VentasRepository ventasRepository;
    public Ventas  insertVenta(Ventas ventas){
        return ventasRepository.save(ventas);
    }
}
