package com.espe.hardware.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HardwareEntity {

    private Long id;
    private String modelo;
    private String categoria;
    private BigDecimal precio;
    private LocalDate fechaCompra;
    private String estado;

    public HardwareEntity(Long id, String modelo, String categoria, BigDecimal precio, LocalDate fechaCompra, String estado) {
        this.id = id;
        this.modelo = modelo;
        this.categoria = categoria;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public String getEstado() {
        return estado;
    }
}