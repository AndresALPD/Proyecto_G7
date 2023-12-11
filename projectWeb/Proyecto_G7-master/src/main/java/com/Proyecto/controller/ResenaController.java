package com.Proyecto.controller;

import com.Proyecto.domain.Resenas;
import com.Proyecto.service.ResenasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenasService resenasService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var resenass = resenasService.getResenass(false);
        model.addAttribute("resenas", resenass);

        model.addAttribute("totalResenas", resenass.size());
        return "/resenas/listado";
    }

    @GetMapping("/nuevo")
    public String resenasNuevo(Resenas resenas) {
        return "/resenas/modifica";
    }

    @PostMapping("/guardar")
    public String resenasGuardar(Resenas resenas) {
        resenasService.save(resenas);
        return "redirect:/resenas/listado";
    }

    @GetMapping("/eliminar/{idResena}")
    public String resenasEliminar(@PathVariable("idResena") Long idResena) {
        Resenas resenas = resenasService.getResenasById(idResena);
        if (resenas != null) {
            resenasService.delete(resenas);
        }
        return "redirect:/resenas/listado";
    }

    @GetMapping("/modificar/{idResena}")
    public String resenasModificar(@PathVariable("idResena") Long idResena, Model model) {
        Resenas resenas = resenasService.getResenasById(idResena);
       
        model.addAttribute("resena", resenas);
        return "/resenas/modifica";
    }
}
