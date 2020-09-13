
package municipales.tramite.dto;

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
public class TramitesEstadosDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estadosSucesores;
}
