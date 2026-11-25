package com.ejemplo.modelo;

import jakarta.persistence.*;
@Entity
@Table(name = "directors")
public class Director {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    protected Director() {}
    public Director(String nombre) { this.nombre = nombre; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
