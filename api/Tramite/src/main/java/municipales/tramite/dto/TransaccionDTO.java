
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
public class TransaccionDTO {
    private Long id;
    Date fechaRegistro;
    PermisoOtorgadoDTO permisoOtorgado;
    String objeto;
    String información;
}
