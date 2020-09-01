/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Requisitos;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IRequisitosService {
    
    public Optional<List<Requisitos>> findAll();

    public Optional<Requisitos> findById(Long id);

    public Requisitos create(Requisitos requisito);

    public Optional<Requisitos> update(Requisitos requisito, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Requisitos>> findByDescripcion(String descripcion);
}
