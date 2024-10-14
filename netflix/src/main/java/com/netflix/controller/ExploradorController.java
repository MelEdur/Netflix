package com.netflix.controller;

import com.netflix.entity.Contenido;
import com.netflix.service.ContenidoService;
import com.netflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExploradorController {

    private final ContenidoService contenidoService;
    private final UsuarioService usuarioService;

    @GetMapping("/explorador")
    public ModelAndView vistaExplorador(int usuarioId, String titulo, String genero, String tipo){

        ModelAndView modelAndView = new ModelAndView("Vista-explorador");
        modelAndView.addObject("usuario",usuarioService.traerPorId(usuarioId));

        List<Contenido> contenidos = contenidoService.traerContenidoPorFiltro(titulo, genero, tipo);
        modelAndView.addObject("resultados",contenidos);


        System.out.println(contenidos);

        return modelAndView;
    }

    @PostMapping("/buscar")
    public ModelAndView buscarContenido(
            @RequestParam("titulo") String titulo,
            @RequestParam("genero") String genero,
            @RequestParam("tipo") String tipo,
            @RequestParam("usuarioId") int usuarioId
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/explorador");
        modelAndView.addObject("titulo",titulo);
        modelAndView.addObject("genero",genero);
        modelAndView.addObject("tipo",tipo);
        modelAndView.addObject("usuarioId",usuarioId);
        return modelAndView;
    }

    @PostMapping("/reproducirPelicula")
    public ModelAndView reproducirPelicula(
            @RequestParam("idContenido") int contenidoId,
            @RequestParam("usuarioId") int usuarioId
    ){
        ModelAndView modelAndView = new ModelAndView("Vista-reproduccion");
        modelAndView.addObject("url",contenidoService.traerPelicula(contenidoId).getReproduccion());
        return modelAndView;
    }

    @PostMapping("/listaEpisodios")
    public ModelAndView verEpisodios(
            @RequestParam("idContenido") int contenidoId,
            @RequestParam("usuarioId") int usuarioId
    ){
        ModelAndView modelAndView = new ModelAndView("Vista-episodios");
        modelAndView.addObject("episodios",contenidoService.traerEpisodios(contenidoId));
        modelAndView.addObject("usuario",usuarioService.traerPorId(usuarioId));
        return modelAndView;
    }

    @PostMapping("/reproducirEpisodio")
    public ModelAndView reproducriEpisodio(
            @RequestParam("idEpisodio") int episodioId,
            @RequestParam("usuarioId") int usuarioId
    ){
        ModelAndView modelAndView = new ModelAndView("Vista-reproduccion");
        modelAndView.addObject("url",contenidoService.traerEpisodio(episodioId).getReproduccion());
        return modelAndView;
    }
}
