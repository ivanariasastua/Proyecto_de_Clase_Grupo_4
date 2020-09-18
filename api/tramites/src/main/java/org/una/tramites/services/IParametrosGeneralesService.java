/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ParametrosGeneralesDTO;
import org.una.tramites.entities.ParametrosGenerales;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IParametrosGeneralesService {

    public Optional<ParametrosGeneralesDTO> update(ParametrosGeneralesDTO pg, Long id);
    
    public Optional<List<ParametrosGeneralesDTO>> findAll();
    
    public ParametrosGeneralesDTO create(ParametrosGeneralesDTO parametros);
    
    public void delete(Long id);
    
    public Optional<ParametrosGeneralesDTO> findById(Long id);
}
