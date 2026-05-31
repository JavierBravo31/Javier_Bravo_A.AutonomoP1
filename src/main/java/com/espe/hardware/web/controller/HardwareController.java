package com.espe.hardware.web.controller;

import com.espe.hardware.domain.service.HardwareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping("/")
    public String inicio() {
        return "Sistema de Inventario ESPE-Tech";
    }

    @GetMapping("/imperativo")
    public String reporteImperativo() {
        return hardwareService.procesarImperativo();
    }

    @GetMapping("/funcional")
    public String reporteFuncional() {
        return hardwareService.procesarFuncional();
    }

    @GetMapping("/resumen")
    public String resumenInventario() {
        return "Resumen de Inventario: se procesaron 10000 equipos tecnologicos usando enfoque imperativo y funcional.";
    }
}