
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Dios
 */
public interface IPermisoOtorgadoRepository extends JpaRepository<PermisoOtorgado, Long>{
    
    public PermisoOtorgado findByUsuarioAndPermiso(Long usuario, Long permiso);
    
    public List<PermisoOtorgado> findByUsuario(Long usuarioId);
    
    public List<PermisoOtorgado> findByPermiso(Long permisoId);
    
    public List<PermisoOtorgado> findByFechaRegistro(Date fechaRegistro);
}
