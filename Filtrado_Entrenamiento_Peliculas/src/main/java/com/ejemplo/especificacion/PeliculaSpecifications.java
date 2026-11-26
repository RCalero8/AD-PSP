package com.ejemplo.especificacion;

import com.ejemplo.modelo.Director;
import com.ejemplo.modelo.Pelicula;
import com.ejemplo.modelo.Productora;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.PredicateSpecification;
import java.math.BigDecimal;
import java.util.Locale;

public final class PeliculaSpecifications {
    private PeliculaSpecifications() {}

    public static PredicateSpecification<Pelicula> tituloContiene(String titulo) {
        return (from, builder) -> {
            if (titulo == null || titulo.isBlank()) return null;
            // TODO 04: sustituir el retorno por el predicado de titulo.
            String patron = "%" + titulo.toLowerCase(Locale.ROOT) + "%";
            return builder.like(builder.lower(from.get("titulo")), patron);
        };
    }

    public static PredicateSpecification<Pelicula> precioMaximo(BigDecimal precioMax) {
        return (from, builder) -> {
            if (precioMax == null) return null;
            // TODO 05: sustituir el retorno por el predicado de precio.
            return builder.lessThanOrEqualTo(from.get("precio"), precioMax);
        };
    }

    public static PredicateSpecification<Pelicula> porDirector(String director) {
        return (from, builder) -> {
            if (director == null || director.isBlank()) return null;
            // TODO 06: realizar el join y devolver el predicado sobre nombre.
            Join<Pelicula, Director> joinDirector = from.join("director", JoinType.INNER);
            return builder.equal(
                    builder.lower(joinDirector.<String>get("nombre")),
                    director.toLowerCase(Locale.ROOT)
            );
        };
    }

    public static PredicateSpecification<Pelicula> combinarFiltros(
            String titulo, BigDecimal precioMax, String director) {
        return PredicateSpecification.allOf(
                tituloContiene(titulo), precioMaximo(precioMax), porDirector(director));
    }

    public static PredicateSpecification<Pelicula> fetchDirector() {
        return (from, builder) -> {
            // TODO 07: realizar fetch LEFT de director y devolver null.
            Fetch<Pelicula, Director> fetchDirector = from.fetch("director", JoinType.LEFT);
            return null;

        };
    }

    public static PredicateSpecification<Pelicula> fetchProductora() {
        return (from, builder) -> {
            // TODO 08: realizar fetch LEFT de productora y devolver null.
            Fetch<Pelicula, Productora> fetchProductora = from.fetch("productora", JoinType.LEFT);
            return null;
        };
    }
}
