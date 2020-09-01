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
import org.una.tramites.entities.TramitesRegistrados;
import org.una.tramites.repositories.ITramitesRegistradosRepository;

/**
 *
 * @author cordo
 */
@Service
public class TramitesRegistradosServiceImplementation implements ITramitesRegistradosService{
    
    @Autowired
    private ITramitesRegistradosRepository tramitesRegistradosRepository;
    
    @Override
    public Optional<List<TramitesRegistrados>> findAll() {
         return Optional.ofNullable(tramitesRegistradosRepository.findAll());
    }

    @Override
    public TramitesRegistrados create(TramitesRegistrados tramitesRegistrados) {
        return tramitesRegistradosRepository.save(tramitesRegistrados);
    }

    @Override
    public Optional<TramitesRegistrados> update(TramitesRegistrados tramitesRegistrados, Long id) {
        if(tramitesRegistradosRepository.findById(id).isPresent())
            return Optional.ofNullable(tramitesRegistradosRepository.save(tramitesRegistrados));
        else
            return null;
    }

    @Override
    public void delete(Long id) {
        tramitesRegistradosRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramitesRegistradosRepository.deleteAll();
    }

    @Override
    public Optional<TramitesRegistrados> findById(Long id) {
        return tramitesRegistradosRepository.findById(id);
    }
}
