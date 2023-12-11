package com.Proyecto.service;

import com.Proyecto.domain.Resenas;
import java.util.List;

public interface ResenasService {

    // Se obtiene un listado de resenass en un List
    List<Resenas> getResenass(boolean activos);

    // Se obtiene un Resenas, a partir del id de un resenas
    Resenas getResenas(Resenas resenas);

    // Se obtiene un Resenas por su ID
    Resenas getResenasById(Long id);

    // Se inserta un nuevo resenas si el id del resenas esta vacío
    // Se actualiza un resenas si el id del resenas NO esta vacío
    void save(Resenas resenas);

    // Se elimina el resenas que tiene el id pasado por parámetro
    void delete(Resenas resenas);
}
