package com.salesianos.dam.primerejemplo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @GetMapping("/hello")
    public Greeting hello(@RequestParam(defaultValue = "WORLD") String name){
        return new Greeting("Hello", name);
    }
    @GetMapping("/hellos")
    public List<Greeting> hellos(){
        return List.of(
                new Greeting("Hello", "World"),
                new Greeting("Hola", "Sevilla"),
                new Greeting("Bonjour", "Paris")
        );
    }
    record Greeting(String greeting, String name){}
}
