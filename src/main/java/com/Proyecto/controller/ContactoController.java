/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.controller;

import com.Proyecto.domain.Contacto;
import com.Proyecto.service.PedidosService;
import com.Proyecto.service.ContactoService;
import com.Proyecto.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
    
    @Autowired
    private ContactoService contactoService;
    
    @GetMapping("/info")
    public String listado(Model model){
        var contactos = contactoService.getContactos(false);
        model.addAttribute("contactos", contactos);
        
        model.addAttribute("totalContactos", contactos.size());
        
        return "/contacto/info";
    }
    
    @GetMapping("/modificar/{idContacto}")
    public String contactoModificar(Contacto contacto, Model model) {
        contacto = contactoService.getContacto(contacto);
        model.addAttribute("contacto", contacto);
        var contactos = contactoService.getContactos(false);
        model.addAttribute("contactos", contactos);
        
        model.addAttribute("totalContactos", contactos.size());
        return "/contacto/modifica";
    }
    
}