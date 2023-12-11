package com.Proyecto.service;

import com.Proyecto.domain.Pedidos;
import java.util.List;

public interface PedidosService {
    
    // Se obtiene un listado de pedidoss en un List
    public List<Pedidos> getPedidoss(boolean activos);
    
   // Se obtiene un Pedidos, a partir del id de un pedidos
    public Pedidos getPedidos(Pedidos pedidos);
    
    // Se inserta un nuevo pedidos si el id del pedidos esta vacío
    // Se actualiza un pedidos si el id del pedidos NO esta vacío
    public void save(Pedidos pedidos);
    
    // Se elimina el pedidos que tiene el id pasado por parámetro
    public void delete(Pedidos pedidos);
}
