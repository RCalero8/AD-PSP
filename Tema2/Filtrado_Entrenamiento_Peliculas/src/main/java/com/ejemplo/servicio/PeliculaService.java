package com.ejemplo.servicio;

import com.ejemplo.dto.PeliculaDto;
import com.ejemplo.modelo.Pelicula;
import com.ejemplo.especificacion.PeliculaSpecifications;
import com.ejemplo.repositorio.PeliculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PeliculaService {
    private final PeliculaRepository repository;
    public PeliculaService(PeliculaRepository repository) { this.repository = repository; }

    // TODO 09: anotar el metodo como transaccion de solo lectura.
    @Transactional(readOnly = true)
    public Page<PeliculaDto> buscar(String titulo, BigDecimal precioMax, String director,
                                 List<String> include, Pageable pageable) {
        List<String> inclusiones = include == null ? List.of() : include;
        PredicateSpecification<Pelicula> filtros =
                PeliculaSpecifications.combinarFiltros(titulo, precioMax, director);
        PredicateSpecification<Pelicula> consulta = filtros;

        // TODO 10: si inclusiones contiene director, agregar fetchDirector con and.
        if(inclusiones.contains("director")){
            consulta = consulta.and(PeliculaSpecifications.fetchDirector());
        }
        // TODO 11: si inclusiones contiene productora, agregar fetchProductora con and.
        if(inclusiones.contains("productora")){
            consulta = consulta.and(PeliculaSpecifications.fetchProductora());
        }
        // TODO 12: sustituir null por repository.findBy(...).
        // Funcion de consulta que debe usarse: q -> q.page(pageable, filtros)
        Page<Pelicula> pagina = repository.findBy(consulta, q ->
                q.page(pageable, filtros));

        // TODO 13: sustituir null por el map de pagina usando PeliculaDto.of.
        return pagina.map(p -> PeliculaDto.of(p, inclusiones));
    }
}
