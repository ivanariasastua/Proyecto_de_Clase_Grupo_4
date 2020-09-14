/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService{

    @Autowired
    private IDepartamentoRepository departamentoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Departamento>> findAll() {
        return Optional.ofNullable(departamentoRepository.findAll());
    }

    @Override
    @Transactional
    public Departamento create(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public Optional<Departamento> update(Departamento departamento, Long id) {
        if(departamentoRepository.findById(id).isPresent())
            return Optional.ofNullable(departamentoRepository.saveAndFlush(departamento));
        else
            return null;
    }
    
    @Override
    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Departamento>> findByNombreAproximate(String nombre) {
        return Optional.ofNullable(departamentoRepository.findByNombreContaining(nombre));
    }

}
