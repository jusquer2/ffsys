package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EnableAutoConfiguration
@Table(name = "personas")
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Integer idPersona;
    @Column(name = "nombrepersona")
    private String nombrePersona;
    private Timestamp fechapersona;
    private Boolean eliminado;

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Timestamp getFechapersona() {
        return fechapersona;
    }

    public void setFechapersona(Timestamp fechapersona) {
        this.fechapersona = fechapersona;
    }
}
