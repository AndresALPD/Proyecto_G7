package com.Proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data
@Entity
@Table(name="pedidos")
public class Pedidos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Long idPedido;
    private String descripcion;
    private String rutaImagen;
    private boolean pagado;

    @OneToMany
    @JoinColumn(name="id_pedido", updatable = false)
    private List<Producto>  productos;
    
    
}
