
package org.una.tramites.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author Dios
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class PermisoOtorgadoDTO {
 
    private Long id; 
    private Usuario usuarioid;   
    private Permiso permisoid; 
    private Date fechaRegistro; 
    private boolean estado; 
}
