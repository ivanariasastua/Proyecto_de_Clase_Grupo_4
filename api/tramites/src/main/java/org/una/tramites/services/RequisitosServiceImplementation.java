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
import org.una.tramites.entities.Requisitos;
import org.una.tramites.repositories.IRequisitosRepository;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class RequisitosServiceImplementation implements IRequisitosService{

    @Autowired
    private IRequisitosRepository reqRepo;
    
    @Override
    public Optional<List<Requisitos>> findAll() {
        return Optional.ofNullable(reqRepo.findAll());
    }

    @Override
    public Optional<Requisitos> findById(Long id) {
        return reqRepo.findById(id);
    }

    @Override
    public Requisitos create(Requisitos requisito) {
        return reqRepo.save(requisito);
    }

    @Override
    public Optional<Requisitos> update(Requisitos requisito, Long id) {
        if(reqRepo.findById(id).isPresent()){
            return Optional.ofNullable(reqRepo.save(requisito));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        reqRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reqRepo.deleteAll();
    }

    @Override
    public Optional<List<Requisitos>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(reqRepo.findByDescripcion(descripcion));
    }

}
