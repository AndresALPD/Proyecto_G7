package com.Proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data
@Entity
@Table(name = "resenas")
public class Resenas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_resena")
    private Long idResena;
    private int calificacion;
    private String titulo;
    private String comentario;

    @OneToMany
    @JoinColumn(name="id_resena", updatable = false)
    private List<Resenas> resenas;

    

}