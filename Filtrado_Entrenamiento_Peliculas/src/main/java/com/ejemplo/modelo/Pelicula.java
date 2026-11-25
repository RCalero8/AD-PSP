package com.ejemplo.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productora_id", nullable = false)
    private Productora productora;
    protected Pelicula() {}
    public Pelicula(String titulo, BigDecimal precio, Director director, Productora productora) {
        this.titulo = titulo; this.precio = precio; this.director = director; this.productora = productora;
    }
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public BigDecimal getPrecio() { return precio; }
    public Director getDirector() { return director; }
    public Productora getProductora() { return productora; }
}
