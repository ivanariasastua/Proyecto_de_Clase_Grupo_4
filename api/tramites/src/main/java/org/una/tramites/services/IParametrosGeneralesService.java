/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ParametrosGenerales;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IParametrosGeneralesService {
    public Optional<List<ParametrosGenerales>> findByNombreAproximate(String nombre);
    
//    public Optional<List<ParametrosGenerales>> findByValor(String valor);
//    
//    public Optional<List<ParametrosGenerales>> findByDescripcion(String descripcion);
    
    public Optional<ParametrosGenerales> update(ParametrosGenerales pg, Long id);
    
    public Optional<List<ParametrosGenerales>> findAll();
    
    public ParametrosGenerales create(ParametrosGenerales parametros);
    
    public void delete(Long id);
    
    public Optional<ParametrosGenerales> findById(Long id);
}
