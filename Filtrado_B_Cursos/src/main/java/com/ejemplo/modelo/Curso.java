package com.ejemplo.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "cursos")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tematica_id", nullable = false)
    private Tematica tematica;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
    protected Curso() {}
    public Curso(String titulo, BigDecimal precio, Tematica tematica, Instructor instructor) {
        this.titulo = titulo; this.precio = precio; this.tematica = tematica; this.instructor = instructor;
    }
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public BigDecimal getPrecio() { return precio; }
    public Tematica getTematica() { return tematica; }
    public Instructor getInstructor() { return instructor; }
}
