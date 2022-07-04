package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@EnableAutoConfiguration
@Table(name = "venta_total")
public class VentaTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idventa_total;
    private Double total;
    private Double pago;
    private Double cambio;
    private Timestamp fechaventa;
    @Column(name = "idcorte")
    private Integer idCorte;
    @OneToMany
    @JoinColumn(name = "idventa_total", insertable = true, updatable = false)
    private List<Ventas> lstVentas;
    @Column(name = "num_orden")
    private Integer numOrden;
    private Boolean uber;
    private Boolean tarjeta;

    public Boolean getUber() {
        return uber;
    }

    public void setUber(Boolean uber) {
        this.uber = uber;
    }

    public Boolean getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Integer getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(Integer numOrden) {
        this.numOrden = numOrden;
    }

    public List<Ventas> getLstVentas() {
        return lstVentas;
    }

    public void setLstVentas(List<Ventas> lstVentas) {
        this.lstVentas = lstVentas;
    }

    public Integer getIdventa_total() {
        return idventa_total;
    }

    public void setIdventa_total(Integer idventa_total) {
        this.idventa_total = idventa_total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    public Timestamp getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Timestamp fechaventa) {
        this.fechaventa = fechaventa;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }
}
