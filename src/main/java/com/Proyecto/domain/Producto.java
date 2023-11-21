package com.Proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String rutaImagen;
    private boolean stock;

    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Pedidos pedidos;
           
}
