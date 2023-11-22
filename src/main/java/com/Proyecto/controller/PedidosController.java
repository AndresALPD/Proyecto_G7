package com.Proyecto.controller;

import com.Proyecto.domain.Pedidos;
import com.Proyecto.service.PedidosService;
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
@RequestMapping("/pedidos")
public class PedidosController {
    
    //Comentario
    
    
    //comentario2
    @Autowired
    private PedidosService pedidosService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var pedidoss = pedidosService.getPedidoss(false);
        model.addAttribute("pedidoss", pedidoss);
        
        model.addAttribute("totalPedidoss", pedidoss.size());
        return "/pedidos/listado";
    }
    
    @GetMapping("/nuevo")
    public String pedidosNuevo(Pedidos pedidos) {
        return "/pedidos/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String pedidosGuardar(Pedidos pedidos,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            pedidosService.save(pedidos);
            pedidos.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "pedidos", 
                            pedidos.getIdPedido()));
        }
        pedidosService.save(pedidos);
        return "redirect:/pedidos/listado";
    }

    @GetMapping("/eliminar/{idPedido}")
    public String pedidosEliminar(Pedidos pedidos) {
        pedidosService.delete(pedidos);
        return "redirect:/pedidos/listado";
    }

    @GetMapping("/modificar/{idPedido}")
    public String pedidosModificar(Pedidos pedidos, Model model) {
        pedidos = pedidosService.getPedidos(pedidos);
        model.addAttribute("pedidos", pedidos);
        return "/pedidos/modifica";
    }
    
}
