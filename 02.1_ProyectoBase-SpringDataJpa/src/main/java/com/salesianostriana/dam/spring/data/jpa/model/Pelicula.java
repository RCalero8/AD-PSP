package com.salesianostriana.dam.spring.data.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "Pelicula")
public class Pelicula {
    @Id
    @GeneratedValue
    public Long id;

    public String titulo;
    public int anioEstreno;

    @ManyToOne
    @JoinColumn(name = "critica_id")
    public Critica critica;

    @ManyToMany
    @JoinColumn(name = "generos_id")
    public Genero generos;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Pelicula pelicula = (Pelicula) o;
        return getId() != null && Objects.equals(getId(), pelicula.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Pelicula(Long id, String titulo, int anioEstreno, Critica critica, Genero genero) {
        this.titulo = titulo;
        this.anioEstreno = anioEstreno;
        this.critica = critica;
        this.generos = genero;
        this.id = id;
    }
}

