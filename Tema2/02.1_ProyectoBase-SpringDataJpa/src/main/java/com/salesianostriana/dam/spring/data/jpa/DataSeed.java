package com.salesianostriana.dam.spring.data.jpa;

import com.salesianostriana.dam.spring.data.jpa.model.Critica;
import com.salesianostriana.dam.spring.data.jpa.model.Pelicula;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataSeed {
    @PostConstruct
    public void cargarDatos(){
    }
}
