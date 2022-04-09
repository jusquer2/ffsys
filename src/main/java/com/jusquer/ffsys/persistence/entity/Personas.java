package com.jusquer.ffsys.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "personas")
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpersona;
    private String nombrepersona;
    private Timestamp fechapersona;

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public Timestamp getFechapersona() {
        return fechapersona;
    }

    public void setFechapersona(Timestamp fechapersona) {
        this.fechapersona = fechapersona;
    }
}
