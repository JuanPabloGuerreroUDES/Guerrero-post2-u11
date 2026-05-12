package com.empresa.catalogo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title = "API Catálogo de Productos",
        version = "1.0",
        description = "API REST para la gestión del catálogo de productos. " +
                      "Implementa operaciones CRUD completas con validación, " +
                      "manejo de errores y logging estructurado.",
        contact = @Contact(
            name = "Guerrero - Ingeniería de Sistemas UDES",
            email = "estudiante@udes.edu.co"
        ),
        license = @License(name = "Universidad de Santander - 2026")
    )
)
@SpringBootApplication
public class CatalogoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }
}
