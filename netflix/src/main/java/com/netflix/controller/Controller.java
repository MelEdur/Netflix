package com.netflix.controller;

import com.netflix.entity.Contenido;
import com.netflix.entity.Pelicula;
import com.netflix.entity.Serie;
import com.netflix.repository.IContenidoRepository;
import com.netflix.service.ContenidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final IContenidoRepository contenidoRepository;
    private final ContenidoService contenidoService;

    @GetMapping("/crear")
    public void crear(){
        contenidoRepository.save(Pelicula.builder()
                .titulo("Wall-E")
                .genero("Infantil")
                .imagen("/fondo.jpg")
                .build());
        contenidoRepository.save(Pelicula.builder()
                .titulo("Los increibles")
                .genero("Infantil")
                .imagen("/wally.jpeg")
                .build());
        contenidoRepository.save(Pelicula.builder()
                .titulo("Piratas del caribe")
                .genero("Aventura")
                .imagen("/static/wally.jpeg")
                .build());
        contenidoRepository.save(Pelicula.builder()
                .titulo("El conjuro")
                .genero("Terror")
                .imagen("/static/wally.jpeg")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Mickey")
                .genero("Infantil")
                .imagen("/static/wally.jpeg")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Fleabag")
                .genero("Comedia")
                .imagen("/static/wally.jpeg")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Dexter")
                .genero("Terror")
                .imagen("/static/wally.jpeg")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Aventura")
                .genero("Aventura")
                .imagen("/static/wally.jpeg")
                .build());
    }

    @GetMapping("/filtro")
    public List<Contenido> filtrar(){

        return contenidoService.traerContenidoPorFiltro("","Terror","pelicula");
    }
}
