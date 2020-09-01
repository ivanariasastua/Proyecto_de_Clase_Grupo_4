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
import org.una.tramites.entities.ParametrosGenerales;
import org.una.tramites.repositories.IParametrosGeneralesRepository;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class ParametrosGeneralesServiceImplementacion implements IParametrosGeneralesService{

    @Autowired
    private IParametrosGeneralesRepository paramGenRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByNombre(String nombre) {
        return Optional.ofNullable(paramGenRepository.findByNombre(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByValor(String valor) {
        return Optional.ofNullable(paramGenRepository.findByValor(valor));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(paramGenRepository.findByDescripcion(descripcion));
    }

    @Override
    @Transactional
    public Optional<ParametrosGenerales> update(ParametrosGenerales pg, Long id) {
        if(paramGenRepository.findById(id).isPresent()){
            return Optional.ofNullable(paramGenRepository.save(pg));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findAll() {
        return Optional.ofNullable(paramGenRepository.findAll());
    }

}
