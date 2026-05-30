package com.espe.hardware.domain.service;

import com.espe.hardware.persistence.HardwareEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HardwareService {

    private final List<HardwareEntity> inventario = new ArrayList<>();

    public HardwareService() {

        for (long i = 1; i <= 10000; i++) {

            String categoria;

            if (i % 3 == 0) {
                categoria = "Laptop";
            } else if (i % 3 == 1) {
                categoria = "PC";
            } else {
                categoria = "Servidor";
            }

            String estado;

            if (i % 5 == 0) {
                estado = "DEBAJA";
            } else {
                estado = "ACTIVO";
            }

            HardwareEntity hardware = new HardwareEntity(
                    i,
                    "Modelo-" + i,
                    categoria,
                    BigDecimal.valueOf(500 + (i % 5000)),
                    LocalDate.now().minusYears(i % 8),
                    estado
            );

            inventario.add(hardware);
        }
    }

    public List<HardwareEntity> obtenerInventario() {
        return inventario;
    }
}