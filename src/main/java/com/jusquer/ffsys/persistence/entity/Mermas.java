package com.jusquer.ffsys.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mermas")
public class Mermas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmerma;
    @OneToOne
    @JoinColumn(name = "idpersona")
    private Personas personas;
    private Integer cantidad;
    @OneToOne
    @JoinColumn(name = "idhotdog", insertable = false,updatable = false)
    private Hotdogs hotdogs;
    @Column(name = "idcorte")
    private Integer idCorte;
    private Timestamp fechamerma;

    public Integer getIdmerma() {
        return idmerma;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public void setIdmerma(Integer idmerma) {
        this.idmerma = idmerma;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Hotdogs getHotdogs() {
        return hotdogs;
    }

    public void setHotdogs(Hotdogs hotdogs) {
        this.hotdogs = hotdogs;
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
