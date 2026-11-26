package com.ejemplo.dto;

import com.ejemplo.modelo.Pelicula;
import java.math.BigDecimal;
import java.util.List;
public record PeliculaDto(Long id, String titulo, BigDecimal precio,
                        String nombreDirector, String nombreProductora) {
    public static PeliculaDto of(Pelicula entidad, List<String> include) {
        return new PeliculaDto(entidad.getId(), entidad.getTitulo(), entidad.getPrecio(),
                include.contains("director") ? entidad.getDirector().getNombre() : null,
                include.contains("productora") ? entidad.getProductora().getNombre() : null);
    }
}
