package com.Proyecto.service.impl;

import com.Proyecto.dao.ProductoDao;
import com.Proyecto.domain.Producto;
import com.Proyecto.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoDao productoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isStock());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
//     //Ejemplo de una consulta con un Query
//    public List<Producto> consultaQuery(double precioInf,double precioSup){
//        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
//    }
//    
//    //Ejemplo de una consulta con un JPQL
//    public List<Producto> consultaJPQL(double precioInf,double precioSup){
//        return productoDao.consultaJPQL(precioInf, precioSup);
//    }


}
