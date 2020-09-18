
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;

/**
 *
 * @author Dios
 */
public interface IArchivoRelacionadoService {
    
    public Optional<ArchivoRelacionadoDTO> findById(Long id);
    
    public Optional<List<ArchivoRelacionadoDTO>> findAll();
    
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistrado(Long id);
    
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistro(Date fechaRegistro);
    
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionado);

    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
