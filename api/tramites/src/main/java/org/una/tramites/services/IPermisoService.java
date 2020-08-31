
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author Dios
 */
public interface IPermisoService {
    
    public Optional<Permiso> findById(Long id);
    
    public Optional<Permiso> findByCodigo(String Codigo);
    
    public Optional<List<Permiso>> findByCodigoAproximate(String codigo);
    
    public Optional<List<Permiso>> findAll();
    
    public Permiso create(Permiso permiso);
    
    public Optional<Permiso> update(Permiso permiso, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
