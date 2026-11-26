package com.ejemplo.controlador;

import com.ejemplo.dto.CursoDto;
import com.ejemplo.servicio.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private final CursoService service;
    public CursoController(CursoService service) { this.service = service; }

    // TODO 01: anotar el metodo con GetMapping sin ruta adicional.
    @GetMapping
    public Page<CursoDto> buscar(
            // TODO 02: anotar los cuatro parametros como RequestParam opcionales.
            @RequestParam(required = false, name = "titulo") String titulo,
            @RequestParam(required = false, name = "precioMax")BigDecimal precioMax,
            @RequestParam(required = false, name = "tematica") String tematica,
            @RequestParam(required = false, name = "include") List<String> include,
            // TODO 03: PageableDefault con page=0, size=5 y sort="id".
            @PageableDefault(page = 0, size= 5, sort = "id") Pageable pageable) {
        return service.buscar(titulo, precioMax, tematica, include, pageable);
    }
}
