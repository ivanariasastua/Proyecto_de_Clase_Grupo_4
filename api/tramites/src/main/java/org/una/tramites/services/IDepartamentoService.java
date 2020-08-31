
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Departamento;


/**
 * 
 * @author Ivan Josué Arias Astúa
 */
public interface IDepartamentoService {
    
    public Optional<Departamento> findById(Long id);
    
    public Optional<List<Departamento>> findAll();
    
    public Departamento create(Departamento departamento);
    
    public Optional<Departamento> update(Departamento departamento, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}