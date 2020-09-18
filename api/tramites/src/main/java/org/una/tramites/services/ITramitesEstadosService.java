/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramitesEstadosDTO;
import org.una.tramites.entities.TramitesEstados;

/**
 *
 * @author cordo
 */
public interface ITramitesEstadosService {
    
    public Optional<List<TramitesEstadosDTO>> findAll();

    public Optional<TramitesEstadosDTO> findById(Long id);

    public TramitesEstadosDTO create(TramitesEstadosDTO tramites);

    public Optional<TramitesEstadosDTO> update(TramitesEstadosDTO tramites, Long id);

    public void delete(Long id);

    public void deleteAll();
}
