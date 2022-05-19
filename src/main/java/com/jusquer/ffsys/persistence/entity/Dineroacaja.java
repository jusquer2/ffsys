package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EnableAutoConfiguration
@Table(name = "dineroacaja")
public class Dineroacaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddineroacaja;
    private Double cantidaddinero;
    @OneToOne
    @JoinColumn(name = "idcorte", insertable = false, updatable = false)
    private Corte corte;
    private Timestamp fechadineroacaja;

    public Integer getIddineroacaja() {
        return iddineroacaja;
    }

    public void setIddineroacaja(Integer iddineroacaja) {
        this.iddineroacaja = iddineroacaja;
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

    public Timestamp getFechadineroacaja() {
        return fechadineroacaja;
    }

    public void setFechadineroacaja(Timestamp fechadineroacaja) {
        this.fechadineroacaja = fechadineroacaja;
    }
}
