package com.netflix.service;

import com.netflix.entity.Contenido;
import com.netflix.entity.Episodio;
import com.netflix.entity.Pelicula;
import com.netflix.entity.Serie;
import com.netflix.repository.IContenidoRepository;
import com.netflix.repository.IEpisodioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContenidoService {

    private final IContenidoRepository contenidoRepository;
    private final IEpisodioRepository episodioRepository;

    public List<Contenido> traerContenidoPorFiltro(String titulo,String genero,String tipo){
        //Trae contenido filtrado por género y título de la BD
        List<Contenido> contenidos = contenidoRepository.findByTituloContainingAndGeneroContaining(titulo,genero);

        if(tipo == null){
            tipo = "";
        }
        //FILTRA POR TIPO DE CONTENIDO
        if(!tipo.isBlank()){
            if(tipo.equals("pelicula")){
                contenidos.removeIf(contenido -> !(contenido instanceof Pelicula));
            }else {
                contenidos.removeIf(contenido -> !(contenido instanceof Serie));
            }
        }
        //DEVUELVE EL CONTENIDO FILTRADO
        return contenidos;
    }

    public Pelicula traerPelicula(int contenidoId){
        return (Pelicula) contenidoRepository.findById(contenidoId).get();
    }

    public List<Episodio> traerEpisodios(int contenidoId){
        Serie serie = (Serie) contenidoRepository.findById(contenidoId).get();
        return serie.getEpisodios();
    }

    public Episodio traerEpisodio(int episodioId){
        return episodioRepository.findById(episodioId).get();
    }

}
