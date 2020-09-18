
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.PermisoDTO;

/**
 *
 * @author Dios
 */
public interface IPermisoService {
    
    public Optional<PermisoDTO> findById(Long id);
    
    public Optional<PermisoDTO> findByCodigo(String Codigo);
    
    public Optional<List<PermisoDTO>> findByCodigoAproximate(String codigo);
    
    public Optional<List<PermisoDTO>> findAll();
    
    public PermisoDTO create(PermisoDTO permiso);
    
    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
