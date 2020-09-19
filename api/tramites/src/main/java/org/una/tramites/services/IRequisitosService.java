/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitosDTO;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IRequisitosService {
    
    public Optional<List<RequisitosDTO>> findAll();

    public Optional<RequisitosDTO> findById(Long id);

    public RequisitosDTO create(RequisitosDTO requisito, Long id);

    public Optional<RequisitosDTO> update(RequisitosDTO requisito, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<RequisitosDTO>> findByDescripcion(String descripcion);
}
