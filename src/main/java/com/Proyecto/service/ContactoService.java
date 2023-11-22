package com.Proyecto.service;

import com.Proyecto.domain.Contacto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ContactoService {
    
    // Se obtiene un listado de contactos en un List
    public List<Contacto> getContactos(boolean activos);
    
   // Se obtiene un Contacto, a partir del id de un contacto
    public Contacto getContacto(Contacto contacto);
    
    // Se inserta un nuevo contacto si el id del contacto esta vacío
    // Se actualiza un contacto si el id del contacto NO esta vacío
    public void save(Contacto contacto);
    
    // Se elimina el contacto que tiene el id pasado por parámetro
    public void delete(Contacto contacto); 
}
