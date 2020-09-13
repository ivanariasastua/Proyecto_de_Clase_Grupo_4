
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
public class TramitesCambioEstadoDTO {
    private Long id;
    private UsuarioDTO usuarioId;
    private TramitesRegistradosDTO tramitesRegistradosId;
    private TramitesCambioEstadoDTO tramitesEstadoId;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
}
