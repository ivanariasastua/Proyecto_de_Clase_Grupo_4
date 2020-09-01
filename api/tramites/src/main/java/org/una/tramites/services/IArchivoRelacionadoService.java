
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author Dios
 */
public interface IArchivoRelacionadoService {
    
    public Optional<ArchivoRelacionado> findById(Long id);
    
    public Optional<List<ArchivoRelacionado>> findAll();
    
    public Optional<List<ArchivoRelacionado>> findByTramiteRegistrado(Long id);
    
    public Optional<List<ArchivoRelacionado>> findByFechaRegistro(Date fechaRegistro);
    
    public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado);

    public Optional<ArchivoRelacionado> update(ArchivoRelacionado ArchivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
