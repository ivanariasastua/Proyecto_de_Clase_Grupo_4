
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author Dios
 */
public interface IRequisitoPresentadoRepository extends JpaRepository<RequisitoPresentado, Long>{
    
    public List<RequisitoPresentado>findByRequisitoId(Long id);
    
    public List<RequisitoPresentado>findByTramiteRegistradoId(Long id);
    
}
