package com.espe.hardware.domain.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface HardwareAIService {

    @UserMessage("""
            Genera un resumen corto del inventario tecnologico.
            Considera la siguiente informacion: {{reporte}}
            Usa menos de 120 caracteres.
            """)
    String generarResumen(@V("reporte") String reporte);
}