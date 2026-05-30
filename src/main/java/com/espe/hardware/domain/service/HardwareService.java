package com.espe.hardware.domain.service;

import com.espe.hardware.persistence.HardwareEntity;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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

    public String procesarImperativo() {

        LocalDate fechaLimite = LocalDate.now().minusYears(5);

        int laptopCantidad = 0;
        int pcCantidad = 0;
        int servidorCantidad = 0;

        BigDecimal laptopTotal = BigDecimal.ZERO;
        BigDecimal pcTotal = BigDecimal.ZERO;
        BigDecimal servidorTotal = BigDecimal.ZERO;

        HardwareEntity laptopMasCaro = null;
        HardwareEntity pcMasCaro = null;
        HardwareEntity servidorMasCaro = null;

        for (HardwareEntity equipo : inventario) {

            if (equipo.getFechaCompra().isAfter(fechaLimite)
                    && equipo.getEstado().equals("ACTIVO")) {

                if (equipo.getCategoria().equals("Laptop")) {

                    laptopCantidad++;
                    laptopTotal = laptopTotal.add(equipo.getPrecio());

                    if (laptopMasCaro == null ||
                            equipo.getPrecio().compareTo(laptopMasCaro.getPrecio()) > 0) {

                        laptopMasCaro = equipo;
                    }

                } else if (equipo.getCategoria().equals("PC")) {

                    pcCantidad++;
                    pcTotal = pcTotal.add(equipo.getPrecio());

                    if (pcMasCaro == null ||
                            equipo.getPrecio().compareTo(pcMasCaro.getPrecio()) > 0) {

                        pcMasCaro = equipo;
                    }

                } else {

                    servidorCantidad++;
                    servidorTotal = servidorTotal.add(equipo.getPrecio());

                    if (servidorMasCaro == null ||
                            equipo.getPrecio().compareTo(servidorMasCaro.getPrecio()) > 0) {

                        servidorMasCaro = equipo;
                    }
                }
            }
        }

        BigDecimal laptopPromedio = BigDecimal.ZERO;
        BigDecimal pcPromedio = BigDecimal.ZERO;
        BigDecimal servidorPromedio = BigDecimal.ZERO;

        if (laptopCantidad > 0) {
            laptopPromedio = laptopTotal.divide(
                    BigDecimal.valueOf(laptopCantidad),
                    2,
                    java.math.RoundingMode.HALF_UP
            );
        }

        if (pcCantidad > 0) {
            pcPromedio = pcTotal.divide(
                    BigDecimal.valueOf(pcCantidad),
                    2,
                    java.math.RoundingMode.HALF_UP
            );
        }

        if (servidorCantidad > 0) {
            servidorPromedio = servidorTotal.divide(
                    BigDecimal.valueOf(servidorCantidad),
                    2,
                    java.math.RoundingMode.HALF_UP
            );
        }

        return """
                REPORTE IMPERATIVO

                Laptop
                Cantidad: %d
                Valor Total: %s
                Promedio: %s
                Equipo Más Caro: %s

                PC
                Cantidad: %d
                Valor Total: %s
                Promedio: %s
                Equipo Más Caro: %s

                Servidor
                Cantidad: %d
                Valor Total: %s
                Promedio: %s
                Equipo Más Caro: %s
                """.formatted(
                laptopCantidad,
                laptopTotal,
                laptopPromedio,
                laptopMasCaro != null ? laptopMasCaro.getModelo() : "N/A",

                pcCantidad,
                pcTotal,
                pcPromedio,
                pcMasCaro != null ? pcMasCaro.getModelo() : "N/A",

                servidorCantidad,
                servidorTotal,
                servidorPromedio,
                servidorMasCaro != null ? servidorMasCaro.getModelo() : "N/A"
        );
    }
    public String procesarFuncional() {

        LocalDate fechaLimite = LocalDate.now().minusYears(5);

        Map<String, List<HardwareEntity>> equiposPorCategoria = inventario.stream()
                .filter(equipo -> equipo.getFechaCompra().isAfter(fechaLimite))
                .filter(equipo -> equipo.getEstado().equals("ACTIVO"))
                .collect(Collectors.groupingBy(HardwareEntity::getCategoria));

        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE FUNCIONAL\n\n");

        equiposPorCategoria.forEach((categoria, equipos) -> {

            BigDecimal valorTotal = equipos.stream()
                    .map(HardwareEntity::getPrecio)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal promedio = BigDecimal.ZERO;

            if (!equipos.isEmpty()) {
                promedio = valorTotal.divide(
                        BigDecimal.valueOf(equipos.size()),
                        2,
                        java.math.RoundingMode.HALF_UP
                );
            }

            Optional<HardwareEntity> equipoMasCaro = equipos.stream()
                    .max(Comparator.comparing(HardwareEntity::getPrecio));

            reporte.append(categoria).append("\n");
            reporte.append("Cantidad: ").append(equipos.size()).append("\n");
            reporte.append("Valor Total: ").append(valorTotal).append("\n");
            reporte.append("Promedio: ").append(promedio).append("\n");
            reporte.append("Equipo Más Caro: ")
                    .append(equipoMasCaro.map(HardwareEntity::getModelo).orElse("N/A"))
                    .append("\n\n");
        });

        return reporte.toString();
    }
}