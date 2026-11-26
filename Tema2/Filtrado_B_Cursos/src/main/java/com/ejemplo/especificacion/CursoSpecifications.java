package com.ejemplo.especificacion;

import com.ejemplo.modelo.Curso;
import com.ejemplo.modelo.Instructor;
import com.ejemplo.modelo.Tematica;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.PredicateSpecification;
import java.math.BigDecimal;
import java.util.Locale;

public final class CursoSpecifications {
    private CursoSpecifications() {}

    public static PredicateSpecification<Curso> tituloContiene(String titulo) {
        return (from, builder) -> {
            if (titulo == null || titulo.isBlank()) return null;
            // TODO 04: sustituir el retorno por el predicado de titulo.
            String patron = "%" + titulo.toLowerCase(Locale.ROOT) + "%";
            return builder.like(
                    builder.lower(from.get("titulo")),
                    patron
            );
        };
    }

    public static PredicateSpecification<Curso> precioMaximo(BigDecimal precioMax) {
        return (from, builder) -> {
            if (precioMax == null) return null;
            // TODO 05: sustituir el retorno por el predicado de precio.
            return builder.lessThanOrEqualTo(from.get("precio"), precioMax);
        };
    }

    public static PredicateSpecification<Curso> porTematica(String tematica) {
        return (from, builder) -> {
            if (tematica == null || tematica.isBlank()) return null;
            // TODO 06: realizar el join y devolver el predicado sobre nombre.
            Join<Curso, Tematica> cursoTematicaJoin = from.join("tematica", JoinType.INNER);
            return builder.equal(
                    builder.lower(from.get("tematica")),
                    cursoTematicaJoin
            );
        };
    }

    public static PredicateSpecification<Curso> combinarFiltros(
            String titulo, BigDecimal precioMax, String tematica) {
        return PredicateSpecification.allOf(
                tituloContiene(titulo), precioMaximo(precioMax), porTematica(tematica));
    }

    public static PredicateSpecification<Curso> fetchTematica() {
        return (from, builder) -> {
            // TODO 07: realizar fetch LEFT de tematica y devolver null.
            Fetch<Curso, Tematica> cursoTematicaFetch = from.fetch("tematica", JoinType.LEFT);
           return null;
        };
    }

    public static PredicateSpecification<Curso> fetchInstructor() {
        return (from, builder) -> {
            // TODO 08: realizar fetch LEFT de instructor y devolver null.
            Fetch<Curso, Instructor> cursoInstructorFetch = from.fetch("instructor", JoinType.LEFT);
            return null;
        };
    }
}
