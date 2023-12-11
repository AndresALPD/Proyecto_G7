package com.Proyecto.service.impl;

import com.Proyecto.dao.PedidosDao;
import com.Proyecto.domain.Pedidos;
import com.Proyecto.service.PedidosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidosServiceImpl implements PedidosService{

    @Autowired
    private PedidosDao pedidosDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> getPedidoss(boolean activos) {
        var lista = pedidosDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isPagado());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Pedidos getPedidos(Pedidos pedidos) {
        return pedidosDao.findById(pedidos.getIdPedido()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Pedidos pedidos) {
        pedidosDao.save(pedidos);
    }

    @Override
    @Transactional
    public void delete(Pedidos pedidos) {
        pedidosDao.delete(pedidos);
    }


}
