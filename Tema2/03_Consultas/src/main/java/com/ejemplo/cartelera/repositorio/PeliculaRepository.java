package com.ejemplo.cartelera.repositorio;

import com.ejemplo.cartelera.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de partida: ¡todavía no contiene métodos de consultas derivadas!
 *
 * En la actividad guiada se añadirán los tres primeros métodos de búsqueda.
 * Después, cada pareja implementará otros cuatro sin escribir SQL ni @Query.
 */
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByDuracionMinutos(int duracionMinutos);
    List<Pelicula> findByDuracionMinutosGreaterThan(int duracionMinutos);
    List<Pelicula> findByDuracionMinutosGreaterThanOrGenero(int duracionMinutos, String genero);
    List<Pelicula> findByTituloContaining(String texto);
    List<Pelicula> findByGenero(String genero);
    List<Pelicula> findByGeneroOrDuracionMinutosLessThan(String genero, int minutos);

    // De entre todas las que se llaman igual se queda con la más larga
    Optional<Pelicula> findFirstByTituloOrderByTitulo(String titulo);
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
    // ACTIVIDAD 3 (guiada): añade aquí los métodos del tutorial.

    // ACTIVIDAD 4 (parejas): añade aquí los métodos de los cuatro retos.
    List<Pelicula> findByGeneroAndDuracionMinutosLessThan(String genero, int minutos);
    long countByGenero(String genero);
    List<Pelicula> findByDisponibleTrueOrderByTituloAsc();
}
