package org.una.tramites.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author Dios
 */
public interface IArchivoRelacionadoRepository extends JpaRepository<ArchivoRelacionado, Long>{
    
    public List<ArchivoRelacionado> findByTramiteRegistradoId(Long id);
    
    public List<ArchivoRelacionado> findByFechaRegistro(Date fechaRegistro);
    
}
