
package municipales.tramite.dto;

import java.util.Date;
/*
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
*/
/**
 *
 * @author Dios
 */

/*
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
*/
class PermisoOtorgadoDTO {
    private Long id; 
    private UsuarioDTO usuarioid;   
    private PermisoDTO permisoid; 
    private Date fechaRegistro; 
    private boolean estado; 
}
