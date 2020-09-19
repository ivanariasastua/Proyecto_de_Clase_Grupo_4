/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.VariacionesDTO;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IVariacionesService {
    
    public Optional<List<VariacionesDTO>> findAll();

    public Optional<VariacionesDTO> findById(Long id);

    public VariacionesDTO create(VariacionesDTO variacion, Long id);

    public Optional<VariacionesDTO> update(VariacionesDTO variacion, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<VariacionesDTO>> findByGrupoContaining(int grupo);
    
    public Optional<List<VariacionesDTO>> findByDescripcion(String descripcion);
}
