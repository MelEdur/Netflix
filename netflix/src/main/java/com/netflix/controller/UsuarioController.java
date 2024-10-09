package com.netflix.controller;

import com.netflix.entity.Plan;
import com.netflix.entity.Usuario;
import com.netflix.service.PlanService;
import com.netflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final PlanService planService;
    private final UsuarioService usuarioService;

    @GetMapping("/registro")
    public ModelAndView vistaRegistro(String mensaje){
        ModelAndView modelAndView = new ModelAndView("Vista-registro");

        //TRAE PLANES DE LA BD
        List<Plan> planes = planService.traerPlanes();

        //AGREGA PLANES Y MENSAJE A LA VISTA
        modelAndView.addObject("Planes",planes);
        modelAndView.addObject("error",mensaje);

        return modelAndView;
    }

    @PostMapping("/registro")
    public ModelAndView registrar(
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("tarjeta") String tarjeta,
            @RequestParam("planId") int planId
    ){
        ModelAndView modelAndView = new ModelAndView();

        Optional<Usuario> usuario = usuarioService.crearUsuario(correo, contrasenia,tarjeta,planService.traerPorId(planId));

        if(usuario.isEmpty()){
            modelAndView.setViewName("redirect:/registro");
            modelAndView.addObject("mensaje", "El correo ingresado ya se encuentra en uso");
        }else {
            modelAndView.setViewName("redirect:/explorador");
            modelAndView.addObject("usuarioId",usuario.get().getIdUsuario());
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView vistaLogIn(String mensaje){
        ModelAndView modelAndView = new ModelAndView("Vista-login");
        modelAndView.addObject("error",mensaje);

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia
    ){
        ModelAndView modelAndView = new ModelAndView();

        try {
            Usuario usuario = usuarioService.login(correo, contrasenia);
            modelAndView.setViewName("redirect:/explorador");
            modelAndView.addObject("usuarioId",usuario.getIdUsuario());

        } catch (Exception e) {
            modelAndView.setViewName("redirect:/login");
            modelAndView.addObject("mensaje",e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping("/modificar")
    public ModelAndView vistaModificar(int usuarioId){
        ModelAndView modelAndView = new ModelAndView("Vista-modificar");
        modelAndView.addObject("usuario",usuarioService.traerPorId(usuarioId));

        return modelAndView;
    }

    @PostMapping("/modificar")
    public ModelAndView modificar(
            @RequestParam("correo")  String correo,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("tarjeta") String tarjeta,
            @RequestParam("planId") int planId,
            @RequestParam("usuarioId") int usuarioId
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/explorador");
        modelAndView.addObject("usuarioId",usuarioService
                .actualizarUsuario(usuarioId,correo,contrasenia,tarjeta,planService.traerPorId(planId)));

        return modelAndView;
    }

    @GetMapping("/baja/{id}")
    public RedirectView eliminar(@PathVariable("id")int id){
        usuarioService.eliminarUsuario(id);
        return new RedirectView("/");
    }

    @GetMapping("")
    public ModelAndView visitante(){
        return new ModelAndView("Vista-visitante");
    }
}
