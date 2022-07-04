package com.jusquer.ffsys.domain.dto;


import com.jusquer.ffsys.persistence.crud.VentasTotalCrudRepository;
import com.jusquer.ffsys.persistence.entity.Mermas;
import com.jusquer.ffsys.persistence.entity.Prestamocaja;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Total {
    @Autowired
    VentasTotalCrudRepository ventaTotalService;
    private List<ProductosTotales> productosTotales;
    private List<Mermas> merma;
    private List<Prestamocaja> prestamoCaja;
    private String totalVendido;
    private String totalPapas;
    private Double total;
    private String dineroCaja;
    private String dineroCajaCorte;
    private Double uberTotal;
    private Double tarjetaTotal;

    public VentasTotalCrudRepository getVentaTotalService() {
        return ventaTotalService;
    }

    public void setVentaTotalService(VentasTotalCrudRepository ventaTotalService) {
        this.ventaTotalService = ventaTotalService;
    }

    public List<ProductosTotales> getProductosTotales() {
        return productosTotales;
    }

    public void setProductosTotales(List<ProductosTotales> productosTotales) {
        this.productosTotales = productosTotales;
    }

    public List<Mermas> getMerma() {
        return merma;
    }

    public void setMerma(List<Mermas> merma) {
        this.merma = merma;
    }

    public List<Prestamocaja> getPrestamoCaja() {
        return prestamoCaja;
    }

    public void setPrestamoCaja(List<Prestamocaja> prestamoCaja) {
        this.prestamoCaja = prestamoCaja;
    }

    public String getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(String totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getTotalPapas() {
        return totalPapas;
    }

    public void setTotalPapas(String totalPapas) {
        this.totalPapas = totalPapas;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDineroCaja() {
        return dineroCaja;
    }

    public void setDineroCaja(String dineroCaja) {
        this.dineroCaja = dineroCaja;
    }

    public String getDineroCajaCorte() {
        return dineroCajaCorte;
    }

    public void setDineroCajaCorte(String dineroCajaCorte) {
        this.dineroCajaCorte = dineroCajaCorte;
    }

    public Double getUberTotal() {
        return uberTotal;
    }

    public void setUberTotal(Double uberTotal) {
        this.uberTotal = uberTotal;
    }

    public Double getTarjetaTotal() {
        return tarjetaTotal;
    }

    public void setTarjetaTotal(Double tarjetaTotal) {
        this.tarjetaTotal = tarjetaTotal;
    }
}
