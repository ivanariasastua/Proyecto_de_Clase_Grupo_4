
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author Dios
 */
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long>{
    
    public List<Transaccion> findByFechaRegistro(Date fechaRegistro);
    
}
