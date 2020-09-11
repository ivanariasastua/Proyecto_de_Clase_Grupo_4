 
package org.una.tramites.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;
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
    private UsuarioDTO usuarioid;   
    private PermisoDTO permisoid;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    private boolean estado; 
}
