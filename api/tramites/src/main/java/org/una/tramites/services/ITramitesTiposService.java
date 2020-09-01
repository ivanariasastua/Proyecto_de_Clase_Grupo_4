/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramitesTipos;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface ITramitesTiposService {
    
    public Optional<List<TramitesTipos>> findAll();

    public Optional<TramitesTipos> findById(Long id);
    
    public TramitesTipos create(TramitesTipos tramite);

    public Optional<TramitesTipos> update(TramitesTipos tramite, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<TramitesTipos>> findByDepartamentoId(Long id);
    
    public Optional<List<TramitesTipos>> findByDescripcion(String descripcion);
}
