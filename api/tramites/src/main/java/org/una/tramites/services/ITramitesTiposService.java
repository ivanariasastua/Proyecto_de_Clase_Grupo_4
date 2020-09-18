/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramitesTiposDTO;
import org.una.tramites.entities.TramitesTipos;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface ITramitesTiposService {
    
    public Optional<List<TramitesTiposDTO>> findAll();

    public Optional<TramitesTiposDTO> findById(Long id);
    
    public TramitesTiposDTO create(TramitesTiposDTO tramite);

    public Optional<TramitesTiposDTO> update(TramitesTiposDTO tramite, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<TramitesTiposDTO>> findByDepartamentoId(Long id);
    
    public Optional<List<TramitesTiposDTO>> findByDescripcion(String descripcion);
}
