package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EnableAutoConfiguration
@Table(name = "prestamocaja")
public class Prestamocaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idprestamocaja;
    @ManyToOne
    @JoinColumn(name = "idpersona")
    private Personas personas;
    private String concepto;
    private Double cantidaddinero;
    @ManyToOne
    @JoinColumn(name = "idcorte", insertable = false, updatable = false)
    private Corte corte;
    private Timestamp fechaprestamo;
    @Column(name = "idcorte")
    private Integer idCorte;
    public Integer getIdprestamocaja() {
        return idprestamocaja;
    }

    public void setIdprestamocaja(Integer idprestamocaja) {
        this.idprestamocaja = idprestamocaja;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getCantidaddinero() {
        return cantidaddinero;
    }

    public void setCantidaddinero(Double cantidaddinero) {
        this.cantidaddinero = cantidaddinero;
    }

    public Corte getCorte() {
        return corte;
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
    }

    public Timestamp getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Timestamp fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }
}
