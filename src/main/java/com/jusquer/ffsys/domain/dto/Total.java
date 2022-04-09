package com.jusquer.ffsys.domain.dto;


import com.jusquer.ffsys.persistence.crud.VentasTotalCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Total {
    @Autowired
    VentasTotalCrudRepository ventaTotalService;
    private List<Map<String, Serializable>> productosTotales;
    private List<Map<String, Serializable>> merma;
    private List<Map<String, Serializable>> prestamoCaja;
    private String totalVendido;
    private String totalPapas;
    private Double total;
    private String dineroCaja;
    private String dineroCajaCorte;
    public Total(){

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


    public void setProductosTotales(List<Map<String, Serializable>> productosTotales) {
        this.productosTotales = productosTotales;
    }


    public void setMerma(List<Map<String, Serializable>> merma) {
        this.merma = merma;
    }


    public void setPrestamoCaja(List<Map<String, Serializable>> prestamoCaja) {
        this.prestamoCaja = prestamoCaja;
    }


    public List<Map<String, Serializable>> getProductosTotales() {
        return productosTotales;
    }

    public List<Map<String, Serializable>> getMerma() {
        return merma;
    }

    public List<Map<String, Serializable>> getPrestamoCaja() {
        return prestamoCaja;
    }
}
