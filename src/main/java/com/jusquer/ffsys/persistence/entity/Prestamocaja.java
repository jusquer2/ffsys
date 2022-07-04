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
    @Column(name = "idprestamocaja")
    private Integer idPrestamoCaja;
    @Column(name = "idpersona")
    private Integer idPersona;
    @ManyToOne
    @JoinColumn(name = "idpersona", insertable = false, updatable = false)
    private Personas persona;
    private String concepto;
    @Column(name = "cantidaddinero")
    private Double cantidadDinero;
    @ManyToOne
    @JoinColumn(name = "idcorte", insertable = false, updatable = false)
    private Corte corte;
    @Column(name = "fechaPrestamo")
    private Timestamp fechaprestamo;
    @Column(name = "idcorte")
    private Integer idCorte;

    public Integer getIdPrestamoCaja() {
        return idPrestamoCaja;
    }

    public void setIdPrestamoCaja(Integer idPrestamoCaja) {
        this.idPrestamoCaja = idPrestamoCaja;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getCantidadDinero() {
        return cantidadDinero;
    }

    public void setCantidadDinero(Double cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
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
