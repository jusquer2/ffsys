package com.jusquer.ffsys.persistence;

import com.jusquer.ffsys.domain.dto.ProductosTotales;
import com.jusquer.ffsys.persistence.crud.VentasTotalCrudRepository;
import com.jusquer.ffsys.persistence.entity.VentaTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository
public class VentasTotalRepository implements com.jusquer.ffsys.domain.repository.VentasTotalRepository {
    @Autowired
    VentasTotalCrudRepository ventasTotalCrudRepository;
    @Override
    public VentaTotal save(VentaTotal ventasTotal) {
        return ventasTotalCrudRepository.save(ventasTotal);
    }

    @Override
    public List<Map<String, Serializable>> findTotalByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findTotalByIdCorte(idcorte);
    }

    @Override
    public List<Map<String, Serializable>> findMermasByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findMermasByIdCorte(idcorte);
    }

    @Override
    public List<Map<String, Serializable>> findPrestamoCajaByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findPrestamoCajaByIdCorte(idcorte);
    }

    @Override
    public Map<String, Serializable> findTotalVendidoByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findTotalVendidoByIdCorte(idcorte);
    }

    @Override
    public Map<String, Serializable> findTotalVendidoPapasByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findTotalVendidoPapasByIdCorte(idcorte);
    }

    @Override
    public Map<String, Serializable> findTotalVendidoPapasProductosByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findTotalVendidoPapasProductosByIdCorte(idcorte);
    }

    @Override
    public List<Map<String, Serializable>> getVentasPorMes(Integer periodo) {
        return ventasTotalCrudRepository.getVentasPorMes(periodo);
    }

    @Override
    public List<Map<String, Serializable>> getPeriodos() {
        return ventasTotalCrudRepository.getPeriodos();
    }

    @Override
    public List<Map<String, Serializable>> getProductosPorMes() {
        return ventasTotalCrudRepository.getProductosPorMes();
    }

    @Override
    public List<Map<String, Serializable>> getPanesRestantes(Integer idcorte) {
        return ventasTotalCrudRepository.getPanesRestantes(idcorte);
    }

    @Override
    public List<VentaTotal> findByIdCorte(Integer idcorte) {
        return ventasTotalCrudRepository.findByIdCorte(idcorte);
    }
}
