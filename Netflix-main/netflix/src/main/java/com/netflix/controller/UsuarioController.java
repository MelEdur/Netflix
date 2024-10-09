package com.netflix.controller;

import com.netflix.entity.Plan;
import com.netflix.entity.Usuario;
import com.netflix.service.PlanService;
import com.netflix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final PlanService planService;
    private final UsuarioService usuarioService;

    @GetMapping("/registro")
    public ModelAndView vistaRegistro(String mensaje) {
        ModelAndView modelAndView = new ModelAndView("Vista-registro");

        // TRAE PLANES DE LA BD
        List<Plan> planes = planService.traerPlanes();

        // AGREGA PLANES Y MENSAJE A LA VISTA
        modelAndView.addObject("Planes", planes);
        modelAndView.addObject("error", mensaje);

        return modelAndView;
    }

    @PostMapping("/registro")
    public ModelAndView registrar(
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("tarjeta") String tarjeta,
            @RequestParam("planId") int planId) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Usuario> usuario = usuarioService.crearUsuario(correo, contrasenia, tarjeta,
                planService.traerPorId(planId));

        if (usuario.isEmpty()) {
            modelAndView.setViewName("redirect:/registro");
            modelAndView.addObject("mensaje", "El correo ingresado ya se encuentra en uso");
        } else {
            modelAndView.setViewName("redirect:/explorador");
            modelAndView.addObject("Usuario", usuario.get());
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView vistaLogIn(String mensaje) {
        ModelAndView modelAndView = new ModelAndView("Vista-login");
        modelAndView.addObject("error", mensaje);

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Usuario usuario = usuarioService.login(correo, contrasenia);
            modelAndView.setViewName("redirect:/explorador");
            modelAndView.addObject("usuario", usuario);
            modelAndView.addObject("contenidos", new ArrayList<>());

        } catch (Exception e) {
            modelAndView.setViewName("redirect:/login");
            modelAndView.addObject("mensaje", e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping("/visitante")
    public ModelAndView vistaVisitante() {
        ModelAndView modelAndView = new ModelAndView("Vista-visitante");
        return modelAndView;
    }
}
