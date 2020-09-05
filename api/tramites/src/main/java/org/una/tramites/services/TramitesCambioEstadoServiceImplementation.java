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
import org.una.tramites.entities.TramitesCambioEstado;
import org.una.tramites.repositories.ITramitesCambioEstadoRepository;

/**
 *
 * @author cordo
 */
@Service
public class TramitesCambioEstadoServiceImplementation implements ITramitesCambioEstadoService {

    @Autowired
    private ITramitesCambioEstadoRepository tramitesCambioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramitesCambioEstado> findById(Long id) {
        return tramitesCambioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesCambioEstado>> findAll() {
        return Optional.ofNullable(tramitesCambioRepository.findAll());
    }

    @Override
    @Transactional
    public TramitesCambioEstado create(TramitesCambioEstado tramitesCambioE) {
        return tramitesCambioRepository.save(tramitesCambioE);
    }

    @Override
    @Transactional
    public Optional<TramitesCambioEstado> update(TramitesCambioEstado tramitesCambioE, Long id) {
        if (tramitesCambioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramitesCambioRepository.save(tramitesCambioE));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesCambioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesCambioRepository.deleteAll();
    }

}
