package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EnableAutoConfiguration
@Table(name = "mermas")
public class Mermas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmerma")
    private Integer idMerma;
    @Column(name = "idpersona")
    private Integer idPersona;
    @ManyToOne
    @JoinColumn(name = "idpersona", insertable = false, updatable = false)
    private Personas persona;
    private Integer cantidad;
    @Column(name = "idhotdog")
    private Integer idHotdog;
    @ManyToOne
    @JoinColumn(name = "idhotdog", insertable = false, updatable = false)
    private Hotdogs hotdog;
    @Column(name = "idcorte")
    private Integer idCorte;
    private Timestamp fechamerma;

    public Hotdogs getHotdog() {
        return hotdog;
    }

    public void setHotdog(Hotdogs hotdog) {
        this.hotdog = hotdog;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Integer getIdMerma() {
        return idMerma;
    }

    public void setIdMerma(Integer idMerma) {
        this.idMerma = idMerma;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdHotdog() {
        return idHotdog;
    }

    public void setIdHotdog(Integer idHotdog) {
        this.idHotdog = idHotdog;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }

    public Timestamp getFechamerma() {
        return fechamerma;
    }

    public void setFechamerma(Timestamp fechamerma) {
        this.fechamerma = fechamerma;
    }
}
