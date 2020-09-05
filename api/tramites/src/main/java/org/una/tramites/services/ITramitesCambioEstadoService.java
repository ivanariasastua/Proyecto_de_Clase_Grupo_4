/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramitesCambioEstado;

/**
 *
 * @author cordo
 */
public interface ITramitesCambioEstadoService {
    public Optional<TramitesCambioEstado> findById(Long id);
    
    public Optional<List<TramitesCambioEstado>> findAll();
 
    public TramitesCambioEstado create(TramitesCambioEstado tramitesCambioE);
    
    public Optional<TramitesCambioEstado> update(TramitesCambioEstado tramitesCambioE, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
