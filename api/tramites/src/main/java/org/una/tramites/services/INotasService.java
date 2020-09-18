/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.NotasDTO;
import org.una.tramites.entities.Notas;

/**
 *
 * @author cordo
 */
public interface INotasService {
    public Optional<List<NotasDTO>> findAll();

    public Optional<NotasDTO> findById(Long id);

    public NotasDTO create(NotasDTO notas);

    public Optional<NotasDTO> update(NotasDTO notas, Long id);

    public void delete(Long id);

    public void deleteAll();
    
   // public Optional<List<NotasDTO>> findByTitulo(String titulo);
}
