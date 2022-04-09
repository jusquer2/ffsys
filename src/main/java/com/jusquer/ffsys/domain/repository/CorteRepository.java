package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.persistence.entity.Corte;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CorteRepository {
    Corte save(Corte corte);
    List<Map<String, Serializable>> getPanesByIdCorte(Integer idcorte);
    List<Map<String, Serializable>> getCortesFecha(String fecha);
    List<Map<String, Serializable>> getTotalVentaCorte(Integer idcorte);

}
