
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Dios
 */
public interface IPermisoOtorgadoService {
    
    public Optional<List<PermisoOtorgado>> findAll();

    public Optional<PermisoOtorgado> findById(Long id);

    public Optional<List<PermisoOtorgado>> findByUsuario(Long id);

    public Optional<List<PermisoOtorgado>> findByPermiso(Long id);
    
    public Optional<List<PermisoOtorgado>> findPermisoOtorgadoByfechaRegistro(Date fechaRegistro);

    public PermisoOtorgado create(PermisoOtorgado permisoOtorgado);

    public Optional<PermisoOtorgado> update(PermisoOtorgado permisoOtorgado, Long id);

    public void delete(Long id);

    public void deleteAll();
        
}
