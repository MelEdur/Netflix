package com.netflix.controller;

import com.netflix.entity.*;
import com.netflix.repository.IUsuarioRepository;
import com.netflix.service.ContenidoService;
import com.netflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProgresoController {

    private final UsuarioService usuarioService;
    private final ContenidoService contenidoService;
    private final IUsuarioRepository usuarioRepository;

    @PostMapping("/guardarProgreso")
    public void guardar(@RequestBody GuardarProgresoRequest request){
        Usuario usuario =  usuarioService.traerPorId(request.getUsuario());

        Perfil perfil = usuario.getPerfiles().get(0);

        boolean existe = false;
        int index = 0;

        Contenido contenido;

        if(request.getPelicula() == 0){
            contenido = contenidoService.traerSeriePorEpisodio(request.getEpisodio());
        }else {
            contenido = contenidoService.traerPelicula(request.getPelicula());
        }

        for(Progreso progreso: perfil.getProgresos()){
            if(progreso.getContenido().getIdContenido() == contenido.getIdContenido() && progreso.getEpisodio() == request.getEpisodio()){
                existe = true;
                index = perfil.getProgresos().indexOf(progreso);
            }
        }


        if(existe){
            perfil.getProgresos().get(index).setSegundo(request.getTime());
        }else {
            Progreso progreso = Progreso.builder()
                    .contenido(contenido)
                    .episodio(request.getEpisodio())
                    .segundo(request.getTime())
                    .build();

            perfil.getProgresos().add(progreso);
        }
        usuarioRepository.save(usuario);
    }
}
