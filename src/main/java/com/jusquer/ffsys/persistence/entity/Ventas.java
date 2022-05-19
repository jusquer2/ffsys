package com.jusquer.ffsys.persistence.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;

@Entity
@EnableAutoConfiguration
@Table(name = "ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private Integer idVenta;
    @Column(name = "idhotdog")
    private Integer idHotDog;
    private String descripcion;
    private Integer cantidad;
    private Double precio;
    @Column(name = "preciounitario")
    private Double precioUnitario;
    @Column(name = "idproducto")
    private Integer idProducto;
    @Column(name = "idventa_total")
    private Integer idVentaTotal;
    private Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdVentaTotal() {
        return idVentaTotal;
    }

    public void setIdVentaTotal(Integer idVentaTotal) {
        this.idVentaTotal = idVentaTotal;
    }

    public Integer getIdHotDog() {
        return idHotDog;
    }

    public void setIdHotDog(Integer idHotDog) {
        this.idHotDog = idHotDog;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }
}
