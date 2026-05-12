package com.empresa.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Respuesta de error de la API")
public class ErrorResponseDTO {

    @Schema(description = "Código HTTP del error", example = "404")
    private int status;

    @Schema(description = "Mensaje descriptivo del error", example = "Producto con id=5 no encontrado")
    private String mensaje;

    @Schema(description = "Momento en que ocurrió el error")
    private LocalDateTime timestamp;

    @Schema(description = "Lista de errores de validación (si aplica)")
    private List<String> errores;

    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDTO(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDTO(int status, String mensaje, List<String> errores) {
        this.status = status;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
        this.errores = errores;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public List<String> getErrores() { return errores; }
    public void setErrores(List<String> errores) { this.errores = errores; }
}
