package com.jusquer.ffsys.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "hotdogs")
public class Hotdogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhotdog")
    private Integer idHotDog;
    @Column(name = "hotdog")
    private String hotDog;
    @Column(name = "precio")
    private Float precio;
    @Column(name = "precioconpapas")
    private Float precioConPapas;
    @Column(name = "preciocps")
    private Float preciocps;
    private Boolean eliminado;
    @Column(name = "prioridad")
    private Integer prioridad;

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getIdHotDog() {
        return idHotDog;
    }

    public void setIdHotDog(Integer idHotDog) {
        this.idHotDog = idHotDog;
    }

    public String getHotDog() {
        return hotDog;
    }

    public void setHotDog(String hotDog) {
        this.hotDog = hotDog;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getPrecioConPapas() {
        return precioConPapas;
    }

    public void setPrecioConPapas(Float precioConPapas) {
        this.precioConPapas = precioConPapas;
    }

    public Float getPreciocps() {
        return preciocps;
    }

    public void setPreciocps(Float preciocps) {
        this.preciocps = preciocps;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}
