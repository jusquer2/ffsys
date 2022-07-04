package com.jusquer.ffsys.domain.service;

import com.jusquer.ffsys.Utils.PrintInvoice;
import com.jusquer.ffsys.domain.repository.VentasTotalRepository;
import com.jusquer.ffsys.persistence.entity.VentaTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class VentaTotalService {
    @Autowired
    VentasTotalRepository ventasTotalRepository;
    @Autowired
    PrintInvoice pi;
    public VentaTotal insertVenta(VentaTotal ventaTotal){
        try {
            pi.getFormatoReporte(ventaTotal);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ventasTotalRepository.save(ventaTotal);
    }
    public List<Map<String, Serializable>> productosTotales(Integer idcorte){
        return ventasTotalRepository.findTotalByIdCorte(idcorte);
    }

    public Map<String, Serializable> getTotalVendidoByIdCorte(Integer idcorte){
        return ventasTotalRepository.findTotalVendidoByIdCorte(idcorte);
    }

    public List<Map<String, Serializable>> findMermasByIdCorte(Integer idcorte){
        return ventasTotalRepository.findMermasByIdCorte(idcorte);
    }
    public List<Map<String, Serializable>> findPrestamoCajaByIdCorte(Integer idcorte){
        return ventasTotalRepository.findPrestamoCajaByIdCorte(idcorte);
    }
    public Map<String, Serializable> findTotalVendidoPapasByIdCorte(Integer idcorte){
        return ventasTotalRepository.findTotalVendidoPapasByIdCorte(idcorte);
    }
    public Map<String, Serializable> findTotalVendidoPapasProductosByIdCorte(Integer idcorte){
        return ventasTotalRepository.findTotalVendidoPapasProductosByIdCorte(idcorte);
    }
    public List<Map<String, Serializable>> getPeriodos(){
        return ventasTotalRepository.getPeriodos();
    }
    public List<Map<String, Serializable>> getVentasPorMes(Integer periodo){
        return ventasTotalRepository.getVentasPorMes(periodo);
    }
    public List<Map<String, Serializable>> getProductosPorMes(){
        return ventasTotalRepository.getProductosPorMes();
    }
    public List<Map<String, Serializable>> getPanesRestantes(Integer idcorte){
        return ventasTotalRepository.getPanesRestantes(idcorte);
    }
    public List<VentaTotal> findByIdCorte(Integer idcorte){
        return ventasTotalRepository.findByIdCorte(idcorte);
    }
    public List<Map<String, Serializable>> getTotalUber(Integer idcorte){
        return ventasTotalRepository.getTotalUber(idcorte);
    }
    public List<Map<String, Serializable>> getTotalTarjeta(Integer idcorte){
        return ventasTotalRepository.getTotalTarjeta(idcorte);
    }
}
