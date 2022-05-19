package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EnableAutoConfiguration
@Table(name = "corte")
public class Corte {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcorte")
    private Integer idCorte;
    @Column(name = "dinerocaja")
    private Double dineroCaja;
    @Column(name = "dinerocajacorte")
    private Double dinaroCajaCorte;
    private String comentario;
    @Column(name = "horaentrada")
    private Timestamp horaEntrada;
    @Column(name = "horasalida")
    private Timestamp horaSalida;
    private Integer panes;

    public Integer getPanes() {
        return panes;
    }

    public void setPanes(Integer panes) {
        this.panes = panes;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }

    public Double getDineroCaja() {
        return dineroCaja;
    }

    public void setDineroCaja(Double dineroCaja) {
        this.dineroCaja = dineroCaja;
    }

    public Double getDinaroCajaCorte() {
        return dinaroCajaCorte;
    }

    public void setDinaroCajaCorte(Double dinaroCajaCorte) {
        this.dinaroCajaCorte = dinaroCajaCorte;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Timestamp horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Timestamp getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Timestamp horaSalida) {
        this.horaSalida = horaSalida;
    }
}
