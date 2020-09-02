/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramitesRegistrados;

/**
 *
 * @author cordo
 */

public interface ITramitesRegistradosService {
    public Optional<List<TramitesRegistrados>> findAll();

    public Optional<TramitesRegistrados> findById(Long id);
    
    public TramitesRegistrados create(TramitesRegistrados tramitesRegistrados);

    public Optional<TramitesRegistrados> update(TramitesRegistrados tramitesRegistrados, Long id);

    public void delete(Long id);

    public void deleteAll();
}
