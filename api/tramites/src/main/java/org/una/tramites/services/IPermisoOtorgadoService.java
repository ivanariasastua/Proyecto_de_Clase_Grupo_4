
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.PermisoOtorgadoDTO;

/**
 *
 * @author Dios
 */
public interface IPermisoOtorgadoService {
    
    public Optional<List<PermisoOtorgadoDTO>> findAll();

    public Optional<PermisoOtorgadoDTO> findById(Long id);

    public Optional<List<PermisoOtorgadoDTO>> findByUsuario(Long id);

    public Optional<List<PermisoOtorgadoDTO>> findByPermiso(Long id);
    
    public Optional<List<PermisoOtorgadoDTO>> findPermisoOtorgadoByfechaRegistro(Date fechaRegistro);

    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgado, Long id);

    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgado, Long id, Long ID);

    public void delete(Long id);

    public void deleteAll();
        
}
