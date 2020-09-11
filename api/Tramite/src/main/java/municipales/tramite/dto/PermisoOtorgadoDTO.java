
package municipales.tramite.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Dios
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString

class PermisoOtorgadoDTO {

public class PermisoOtorgadoDTO {
    private Long id; 
    private UsuarioDTO usuarioid;   
    private PermisoDTO permisoid; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    private boolean estado; 
}
