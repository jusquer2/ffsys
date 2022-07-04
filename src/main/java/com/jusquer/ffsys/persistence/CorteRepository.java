package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.persistence.crud.CorteCrudRepository;
import com.jusquer.ffsys.persistence.entity.Corte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Map<String, Serializable>> getPanesByIdCorteEmpty(Integer idcorte) {
        return corteCrudRepository.getPanesByIdCorteEmpty(idcorte);
    }

    @Override
    public List<Corte> getCortesFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date =  sdf.parse(fecha);
        }catch (Exception e){
            date = new Date();
        }

        Timestamp ts=new Timestamp(date.getTime());
        return corteCrudRepository.findByHoraEntrada(ts);
    }

    @Override
    public List<Map<String, Serializable>> getTotalVentaCorte(Integer idcorte) {
        return corteCrudRepository.getTotalVentaCorte(idcorte);
    }

    @Override
    public Optional<Corte> findByIdCorte(Integer idcorte) {
        return corteCrudRepository.findById(idcorte);
    }
}
