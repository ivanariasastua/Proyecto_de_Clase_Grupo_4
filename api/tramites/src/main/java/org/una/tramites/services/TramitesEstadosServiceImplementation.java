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
import org.una.tramites.entities.TramitesEstados;
import org.una.tramites.repositories.ITramitesEstadosRepository;

/**
 *
 * @author cordo
 */
@Service
public class TramitesEstadosServiceImplementation implements ITramitesEstadosService {

    @Autowired
    private ITramitesEstadosRepository tramRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramitesEstados> findById(Long id) {
        return tramRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesEstados>> findAll() {
        return Optional.ofNullable(tramRepository.findAll());
    }

    @Override
    @Transactional
    public TramitesEstados create(TramitesEstados tramites) {
        return tramRepository.save(tramites);
    }

    @Override
    public Optional<TramitesEstados> update(TramitesEstados tramites, Long id) {
        if (tramRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramRepository.save(tramites));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tramRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramRepository.deleteAll();
    }
}
