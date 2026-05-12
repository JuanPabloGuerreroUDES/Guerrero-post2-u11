package com.empresa.catalogo.exception;

public class RecursoNoEncontradoException extends RuntimeException {

    private final String recurso;
    private final Long id;

    public RecursoNoEncontradoException(String recurso, Long id) {
        super(recurso + " con id=" + id + " no encontrado");
        this.recurso = recurso;
        this.id = id;
    }

    public String getRecurso() { return recurso; }
    public Long getId() { return id; }
}
