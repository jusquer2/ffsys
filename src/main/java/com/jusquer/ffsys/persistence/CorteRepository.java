package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.persistence.crud.CorteCrudRepository;
import com.jusquer.ffsys.persistence.entity.Corte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository
public class CorteRepository implements com.jusquer.ffsys.domain.repository.CorteRepository {
    @Autowired
    CorteCrudRepository corteCrudRepository;
    @Override
    public Corte save(Corte corte) {
        return corteCrudRepository.save(corte);
    }

    @Override
    public List<Map<String, Serializable>> getPanesByIdCorte(Integer idcorte) {
        return corteCrudRepository.getPanesByIdCorte(idcorte);
    }

    @Override
    public List<Map<String, Serializable>> getCortesFecha(String fecha) {
        return corteCrudRepository.getCortesFecha(fecha);
    }

    @Override
    public List<Map<String, Serializable>> getTotalVentaCorte(Integer idcorte) {
        return corteCrudRepository.getTotalVentaCorte(idcorte);
    }
}
