package com.jusquer.ffsys.domain.repository;

import com.jusquer.ffsys.domain.dto.ProductosTotales;
import com.jusquer.ffsys.persistence.entity.VentaTotal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface VentasTotalRepository {
    VentaTotal save(VentaTotal ventasTotal);
    List<Map<String, Serializable>> findTotalByIdCorte(Integer idcorte);
    List<Map<String, Serializable>> findMermasByIdCorte(Integer idcorte);
    List<Map<String, Serializable>> findPrestamoCajaByIdCorte(Integer idcorte);
    Map<String, Serializable> findTotalVendidoByIdCorte(Integer idcorte);
    Map<String, Serializable> findTotalVendidoPapasByIdCorte(Integer idcorte);
    Map<String, Serializable> findTotalVendidoPapasProductosByIdCorte(Integer idcorte);
    List<Map<String, Serializable>> getVentasPorMes(Integer periodo);
    List<Map<String, Serializable>> getPeriodos();
    List<Map<String, Serializable>> getProductosPorMes();
    List<Map<String, Serializable>> getPanesRestantes(Integer idcorte);
    List<VentaTotal> findByIdCorte(Integer idcorte);

}
