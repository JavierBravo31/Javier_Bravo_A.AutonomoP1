# ACTIVIDAD AUTÓNOMO

## Universidad de las Fuerzas Armadas ESPE

**Asignatura:** Programación Avanzada  
**Docente:** Paulo Cesar Galarza Sánchez  
**Estudiante:** Javier Neicer Bravo Macias  
**NRC:** 30405

---

# Tema

Comparar la eficiencia y legibilidad de distintos paradigmas de programación mediante la resolución de un problema algorítmico complejo.

---

# Descripción del Proyecto

Se desarrolló una aplicación Spring Boot para la gestión de un inventario tecnológico compuesto por equipos de hardware.

La solución implementa la arquitectura trabajada en clases mediante las siguientes capas:

- Persistencia (HardwareEntity)
- Negocio (HardwareService)
- Web (HardwareController)
- AI (HardwareAIService)

El sistema genera un inventario de 10.000 equipos tecnológicos y procesa la información utilizando dos enfoques diferentes:

1. Paradigma Imperativo
2. Paradigma Funcional (Streams API)

---

# Endpoints Implementados

| Endpoint | Descripción |
|-----------|------------|
| / | Página principal |
| /imperativo | Reporte utilizando programación imperativa |
| /funcional | Reporte utilizando programación funcional |
| /resumen | Resumen general del inventario |

---

# Implementación Imperativa

La solución imperativa utiliza:

- Ciclos for
- Estructuras if - else
- Acumuladores manuales
- Comparaciones explícitas

Características:

- Mayor control sobre el flujo de ejecución.
- Fácil seguimiento paso a paso.
- Requiere más líneas de código.

---

# Implementación Funcional

La solución funcional utiliza:

- Streams API
- filter()
- groupingBy()
- Collectors
- Optional

Características:

- Código más compacto.
- Mejor legibilidad para operaciones sobre colecciones.
- Menor cantidad de instrucciones repetitivas.

---

# Comparación de Resultados

| Criterio | Paradigma Imperativo | Paradigma Funcional |
|-----------|---------------------|---------------------|
| Líneas de código | Mayor cantidad | Menor cantidad |
| Legibilidad | Media | Alta |
| Mantenimiento | Más complejo | Más sencillo |
| Reutilización | Limitada | Alta |
| Escalabilidad | Buena | Muy buena |

---

# Conclusiones

La implementación imperativa proporciona un mayor control sobre cada paso del algoritmo, pero requiere más código y mantenimiento.

La implementación funcional mediante Streams permite desarrollar soluciones más compactas y legibles, facilitando el procesamiento de grandes colecciones de datos.

Para este caso de estudio, ambos enfoques producen los mismos resultados, aunque el paradigma funcional ofrece una solución más moderna y mantenible.

---

# Tecnologías Utilizadas

- Java 21
- Spring Boot 4.0.6
- Gradle
- LangChain4j
- GitHub
