package com.ejemplo.modelo;

import jakarta.persistence.*;
@Entity
@Table(name = "productoras")
public class Productora {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    protected Productora() {}
    public Productora(String nombre) { this.nombre = nombre; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
