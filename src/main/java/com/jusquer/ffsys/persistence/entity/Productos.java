package com.jusquer.ffsys.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idProducto;
    private String producto;
    private Double precio;
    private Boolean ispapas;
    private Boolean eliminado;
    private Integer prioridad;

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double prpecio) {
        this.precio = prpecio;
    }

    public Boolean getIspapas() {
        return ispapas;
    }

    public void setIspapas(Boolean ispapas) {
        this.ispapas = ispapas;
    }
}
