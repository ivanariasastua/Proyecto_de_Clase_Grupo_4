/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramitesEstados;

/**
 *
 * @author cordo
 */
public interface ITramitesEstadosService {

    public Optional<TramitesEstados> findById(Long id);

    public Optional<List<TramitesEstados>> findAll();

    public TramitesEstados create(TramitesEstados tramites);

    public Optional<TramitesEstados> update(TramitesEstados tramites, Long id);

    public void delete(Long id);

    public void deleteAll();
}
