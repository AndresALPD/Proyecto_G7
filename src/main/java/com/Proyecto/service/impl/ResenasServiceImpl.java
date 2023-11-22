package com.Proyecto.service.impl;
import com.Proyecto.dao.ResenasDao;
import com.Proyecto.domain.Resenas;
import com.Proyecto.service.ResenasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResenasServiceImpl implements ResenasService {

    @Autowired
    private ResenasDao resenasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Resenas> getResenass(boolean activos) {
        return resenasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resenas getResenas(Resenas resenas) {
        return resenasDao.findById(resenas.getIdResena()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Resenas getResenasById(Long id) {
        return resenasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Resenas resenas) {
        resenasDao.save(resenas);
    }

    @Override
    @Transactional
    public void delete(Resenas resenas) {
        resenasDao.delete(resenas);
    }
}
