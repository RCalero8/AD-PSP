package com.ejemplo.controlador;

import com.ejemplo.dto.PeliculaDto;
import com.ejemplo.servicio.PeliculaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    private final PeliculaService service;
    public PeliculaController(PeliculaService service) { this.service = service; }

    @GetMapping
    public Page<PeliculaDto> buscar(
            // TODO 02: anotar los cuatro parametros como RequestParam opcionales.
            @RequestParam(required = false, name = "titulo") String titulo ,
            @RequestParam(required = false, name = "precioMax") BigDecimal precioMax,
            @RequestParam(required = false, name = "director") String director,
            @RequestParam(required = false, name = "include") List<String> include,
            // TODO 03: PageableDefault con page=0, size=5 y sort="id".
            @PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable) {
        return service.buscar(titulo, precioMax, director, include, pageable);
    }
}
