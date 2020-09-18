/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramitesCambioEstadoDTO;
import org.una.tramites.entities.TramitesCambioEstado;

/**
 *
 * @author cordo
 */
public interface ITramitesCambioEstadoService {
    public Optional<List<TramitesCambioEstadoDTO>> findAll();
    
    public Optional<TramitesCambioEstadoDTO> findById(Long id);
 
    public TramitesCambioEstadoDTO create(TramitesCambioEstadoDTO tramitesCambioE);
    
    public Optional<TramitesCambioEstadoDTO> update(TramitesCambioEstadoDTO tramitesCambioE, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
