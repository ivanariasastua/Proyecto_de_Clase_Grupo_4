package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author Dios
 */
public interface IPermisoRepository extends JpaRepository<Permiso, Long>{
    
    public Permiso findbyCodigoContaining(String codigo);
    
    public List<Permiso> findbyFechaRegistro(Date fechaRegistro);
    
    public List<Permiso> fingbyFechaModificacion(Date fechaModificacion);
}
