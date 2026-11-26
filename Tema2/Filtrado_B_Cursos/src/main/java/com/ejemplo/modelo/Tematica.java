package com.ejemplo.modelo;

import jakarta.persistence.*;
@Entity
@Table(name = "tematicas")
public class Tematica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    protected Tematica() {}
    public Tematica(String nombre) { this.nombre = nombre; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
