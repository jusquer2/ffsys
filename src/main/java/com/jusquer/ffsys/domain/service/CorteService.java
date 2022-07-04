package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.domain.repository.CorteRepository;
import com.jusquer.ffsys.persistence.entity.Corte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CorteService {
    @Autowired
    CorteRepository corteRepository;
    public Corte crearCorte(Corte corte){
        if(corte.getIdCorte()!=null && corte.getIdCorte()>0){
            Corte corteAux = corteRepository.findByIdCorte(corte.getIdCorte()).get();
            corteAux.setDinaroCajaCorte(corte.getDinaroCajaCorte());
            corteAux.setComentario(corte.getComentario());
            corteAux.setHoraSalida(new Timestamp(new Date().getTime()));
            corte = corteAux;
        }
        return corteRepository.save(corte);
    }
    public List<Map<String, Serializable>> getPanesByIdCorte(Integer idcorte){
        return corteRepository.getPanesByIdCorte(idcorte);
    }
    public List<Map<String, Serializable>> getPanesByIdCorteEmpty(Integer idcorte){
        return corteRepository.getPanesByIdCorteEmpty(idcorte);
    }
    public List<Corte> getCortesFecha(String fecha){
        return corteRepository.getCortesFecha(fecha);
    }
    public List<Map<String, Serializable>> getTotalVentaCorte(Integer idcorte){
        return corteRepository.getTotalVentaCorte(idcorte);
    }
}
