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
                .build());
        contenidoRepository.save(Pelicula.builder()
                .titulo("Los increibles")
                .genero("Infantil")
                .build());
        contenidoRepository.save(Pelicula.builder()
                .titulo("Piratas del caribe")
                .genero("Aventura")
                .build());
        contenidoRepository.save(Pelicula.builder()
                .titulo("El conjuro")
                .genero("Terror")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Mickey")
                .genero("Infantil")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Fleabag")
                .genero("Comedia")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Dexter")
                .genero("Terror")
                .build());
        contenidoRepository.save(Serie.builder()
                .titulo("Aventura")
                .genero("Aventura")
                .build());
    }

    @GetMapping("/filtro")
    public List<Contenido> filtrar(){

        return contenidoService.traerContenidoPorFiltro("","Terror","pelicula");
    }
}
