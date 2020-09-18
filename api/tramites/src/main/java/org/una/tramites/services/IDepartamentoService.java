
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;


/**
 * 
 * @author Ivan Josué Arias Astúa
 */
public interface IDepartamentoService {
    
    public Optional<DepartamentoDTO> findById(Long id);
    
    public Optional<List<DepartamentoDTO>> findAll();
    
    public DepartamentoDTO create(DepartamentoDTO departamento);
    
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
    
    public Optional<List<DepartamentoDTO>> findByNombreAproximate(String nombre);
    
}