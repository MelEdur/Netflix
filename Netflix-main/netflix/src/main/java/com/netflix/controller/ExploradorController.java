package com.netflix.controller;

import com.netflix.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ExploradorController {

    @GetMapping("/explorador")
    public ModelAndView vistaExplorador(Usuario usuario){

        ModelAndView modelAndView = new ModelAndView("Vista-explorador");
        modelAndView.addObject("usuario",usuario);

        return modelAndView;

    }
}
