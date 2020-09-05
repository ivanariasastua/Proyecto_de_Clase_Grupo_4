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
import org.una.tramites.entities.Variaciones;
import org.una.tramites.repositories.IVariacionesRepository;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class VariacionesServiceImplementation implements IVariacionesService{
    
    @Autowired
    private IVariacionesRepository varRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variaciones>> findAll() {
        return Optional.ofNullable(varRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Variaciones> findById(Long id) {
        return varRepository.findById(id);
    }

    @Override
    @Transactional
    public Variaciones create(Variaciones variacion) {
        return varRepository.save(variacion);
    }

    @Override
    @Transactional
    public Optional<Variaciones> update(Variaciones variacion, Long id) {
        if(varRepository.findById(id).isPresent())
            return Optional.ofNullable(varRepository.save(variacion));
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        varRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        varRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variaciones>> findByGrupo(String grupo) {
        return Optional.ofNullable(varRepository.findByGrupo(grupo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variaciones>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(varRepository.findByDescripcion(descripcion));
    }

}
