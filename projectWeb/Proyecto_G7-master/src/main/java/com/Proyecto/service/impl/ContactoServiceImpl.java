/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.dao.ContactoDao;
import com.Proyecto.domain.Contacto;
import com.Proyecto.domain.Contacto;
import com.Proyecto.service.ContactoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author soporte
 */
@Service
public class ContactoServiceImpl implements ContactoService{


    @Autowired
    private ContactoDao contactoDao;
    
    @Override
        @Transactional(readOnly = true)
    public List<Contacto> getContactos(boolean activo) {
        var contactos = contactoDao.findAll();
        return contactos;
    }
    
        @Override
    @Transactional(readOnly = true)
    public Contacto getContacto(Contacto contacto) {
        return contactoDao.findById(contacto.getIdContacto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Contacto contacto) {
        contactoDao.save(contacto);
    }

    @Override
    @Transactional
    public void delete(Contacto contacto) {
        contactoDao.delete(contacto);
    }
}
