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
    private final PeliculaRepository principales;
    private final DirectorRepository primeros;
    private final ProductoraRepository segundos;
    private final TransactionTemplate tx;
    public DataSeed(PeliculaRepository principales, DirectorRepository primeros,
                    ProductoraRepository segundos, TransactionTemplate tx) {
        this.principales = principales; this.primeros = primeros;
        this.segundos = segundos; this.tx = tx;
    }
    @PostConstruct
    public void cargar() {
        tx.executeWithoutResult(status -> {
            if (principales.count() > 0) return;
            Director a0 = primeros.save(new Director("Elena"));
            Director a1 = primeros.save(new Director("Marcos"));
            Director a2 = primeros.save(new Director("Sara"));
            Director a3 = primeros.save(new Director("Pablo"));
            Productora b0 = segundos.save(new Productora("Faro Films"));
            Productora b1 = segundos.save(new Productora("Luna Cine"));
            Productora b2 = segundos.save(new Productora("Sur Films"));
            Productora b3 = segundos.save(new Productora("Prisma Cine"));
            principales.saveAll(List.of(
                    new Pelicula("Viaje al bosque", new BigDecimal("1.00"), a0, b0),
                    new Pelicula("Viaje a las estrellas", new BigDecimal("2.00"), a0, b1),
                    new Pelicula("La ciudad despierta", new BigDecimal("3.00"), a1, b2),
                    new Pelicula("El cristal azul", new BigDecimal("4.00"), a2, b3),
                    new Pelicula("Viaje al oceano", new BigDecimal("5.00"), a0, b2),
                    new Pelicula("El ultimo imperio", new BigDecimal("6.00"), a3, b0),
                    new Pelicula("Noche de verano", new BigDecimal("7.00"), a1, b1),
                    new Pelicula("La luna invisible", new BigDecimal("8.00"), a2, b2),
                    new Pelicula("Viaje al polo", new BigDecimal("9.00"), a0, b3),
                    new Pelicula("El castillo perdido", new BigDecimal("10.00"), a3, b1),
                    new Pelicula("Huellas del desierto", new BigDecimal("1.50"), a1, b0),
                    new Pelicula("El secreto del faro", new BigDecimal("2.50"), a2, b1),
                    new Pelicula("Viaje a las islas", new BigDecimal("9.50"), a0, b0),
                    new Pelicula("El mapa del cielo", new BigDecimal("4.50"), a3, b2),
                    new Pelicula("La montana azul", new BigDecimal("5.50"), a1, b3),
                    new Pelicula("Colores de invierno", new BigDecimal("6.50"), a2, b0),
                    new Pelicula("Viaje al templo", new BigDecimal("7.50"), a0, b1),
                    new Pelicula("El puerto lejano", new BigDecimal("8.50"), a3, b3)));
        });
    }
}
