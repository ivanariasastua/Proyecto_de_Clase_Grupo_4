
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

public class TramiteCambioEstadoDTO {
    private Long id;
    private UsuarioDTO usuarioId;
    private TramiteRegistradoDTO tramitesRegistradosId;
    private TramiteCambioEstadoDTO tramitesEstadoId;
    private Date fechaRegistro; 
}
