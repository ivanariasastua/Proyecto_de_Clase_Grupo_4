
package municipales.tramite.dto;

import java.util.Date;
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

public class RequisitoPresentadoDTO {
    private Long id;
    private Date fechaRegistro;
    private TramiteRegistradoDTO tramiteRegistradoId;
    private RequisitoDTO requisitoId;
}
