/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Notas;

/**
 *
 * @author cordo
 */
public interface INotasService {
    public Optional<List<Notas>> findAll();

    public Optional<Notas> findById(Long id);

    public Notas create(Notas variacion);

    public Optional<Notas> update(Notas notas, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<Notas> findByTitulo(String grupo);
}
