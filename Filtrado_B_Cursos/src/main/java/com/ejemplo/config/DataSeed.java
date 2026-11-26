package com.ejemplo.config;

import com.ejemplo.modelo.*;
import com.ejemplo.repositorio.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeed {
    private final CursoRepository principales;
    private final TematicaRepository primeros;
    private final InstructorRepository segundos;
    private final TransactionTemplate tx;
    public DataSeed(CursoRepository principales, TematicaRepository primeros,
                    InstructorRepository segundos, TransactionTemplate tx) {
        this.principales = principales; this.primeros = primeros;
        this.segundos = segundos; this.tx = tx;
    }
    @PostConstruct
    public void cargar() {
        tx.executeWithoutResult(status -> {
            if (principales.count() > 0) return;
            Tematica a0 = primeros.save(new Tematica("Programacion"));
            Tematica a1 = primeros.save(new Tematica("Fotografia"));
            Tematica a2 = primeros.save(new Tematica("Diseno"));
            Tematica a3 = primeros.save(new Tematica("Marketing"));
            Instructor b0 = segundos.save(new Instructor("Ana Lopez"));
            Instructor b1 = segundos.save(new Instructor("Bruno Ruiz"));
            Instructor b2 = segundos.save(new Instructor("Carla Gil"));
            Instructor b3 = segundos.save(new Instructor("Diego Vega"));
            principales.saveAll(List.of(
                    new Curso("Java desde cero", new BigDecimal("10.00"), a0, b0),
                    new Curso("Java con Spring", new BigDecimal("20.00"), a0, b1),
                    new Curso("Fotografia urbana", new BigDecimal("30.00"), a1, b2),
                    new Curso("Diseno de carteles", new BigDecimal("40.00"), a2, b3),
                    new Curso("Java y bases de datos", new BigDecimal("50.00"), a0, b2),
                    new Curso("Marketing digital", new BigDecimal("60.00"), a3, b0),
                    new Curso("Fotografia nocturna", new BigDecimal("70.00"), a1, b1),
                    new Curso("Diseno editorial", new BigDecimal("80.00"), a2, b2),
                    new Curso("Java avanzado", new BigDecimal("90.00"), a0, b3),
                    new Curso("Marketing de contenidos", new BigDecimal("100.00"), a3, b1),
                    new Curso("Fotografia de viajes", new BigDecimal("15.00"), a1, b0),
                    new Curso("Diseno de interfaces", new BigDecimal("25.00"), a2, b1),
                    new Curso("Java y seguridad", new BigDecimal("95.00"), a0, b0),
                    new Curso("Marketing para tiendas", new BigDecimal("45.00"), a3, b2),
                    new Curso("Fotografia de producto", new BigDecimal("55.00"), a1, b3),
                    new Curso("Diseno de logotipos", new BigDecimal("65.00"), a2, b0),
                    new Curso("Java y concurrencia", new BigDecimal("75.00"), a0, b1),
                    new Curso("Marketing por correo", new BigDecimal("85.00"), a3, b3)));
        });
    }
}
