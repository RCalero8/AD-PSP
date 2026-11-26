package com.ejemplo.servicio;

import com.ejemplo.dto.CursoDto;
import com.ejemplo.modelo.Curso;
import com.ejemplo.especificacion.CursoSpecifications;
import com.ejemplo.repositorio.CursoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CursoService {
    private final CursoRepository repository;
    public CursoService(CursoRepository repository) { this.repository = repository; }

    // TODO 09: anotar el metodo como transaccion de solo lectura.
    @Transactional(readOnly = true)
    public Page<CursoDto> buscar(String titulo, BigDecimal precioMax, String tematica,
                                 List<String> include, Pageable pageable) {
        List<String> inclusiones = include == null ? List.of() : include;
        PredicateSpecification<Curso> filtros =
                CursoSpecifications.combinarFiltros(titulo, precioMax, tematica);
        PredicateSpecification<Curso> consulta = filtros;

        // TODO 10: si inclusiones contiene tematica, agregar fetchTematica con and.
        if(inclusiones.contains("tematica")){
            consulta = consulta.and(CursoSpecifications.fetchTematica());
        }
        // TODO 11: si inclusiones contiene instructor, agregar fetchInstructor con and.
        if(inclusiones.contains("instructor")){
            consulta = consulta.and(CursoSpecifications.fetchInstructor());
        }
        // TODO 12: sustituir null por repository.findBy(...).
        // Funcion de consulta que debe usarse: q -> q.page(pageable, filtros)
        Page<Curso> pagina = repository.findBy(consulta, q ->
                q.page(pageable, filtros));

        // TODO 13: sustituir null por el map de pagina usando CursoDto.of.
        return pagina.map(p -> CursoDto.of(p, include));
    }
}
