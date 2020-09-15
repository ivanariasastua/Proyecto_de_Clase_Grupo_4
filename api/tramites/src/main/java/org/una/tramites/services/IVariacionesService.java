/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Variaciones;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IVariacionesService {
    public Optional<List<Variaciones>> findAll();

    public Optional<Variaciones> findById(Long id);

    public Variaciones create(Variaciones variacion);

    public Optional<Variaciones> update(Variaciones variacion, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Variaciones>> findByGrupoContaining(int grupo);
    
    public Optional<List<Variaciones>> findByDescripcion(String descripcion);
}
