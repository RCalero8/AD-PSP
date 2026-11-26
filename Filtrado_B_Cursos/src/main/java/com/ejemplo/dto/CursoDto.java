package com.ejemplo.dto;

import com.ejemplo.modelo.Curso;
import java.math.BigDecimal;
import java.util.List;
public record CursoDto(Long id, String titulo, BigDecimal precio,
                        String nombreTematica, String nombreInstructor) {
    public static CursoDto of(Curso entidad, List<String> include) {
        return new CursoDto(entidad.getId(), entidad.getTitulo(), entidad.getPrecio(),
                include.contains("tematica") ? entidad.getTematica().getNombre() : null,
                include.contains("instructor") ? entidad.getInstructor().getNombre() : null);
    }
}
