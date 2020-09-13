
package municipales.tramite.dto;

import java.util.ArrayList;
import java.util.List;
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

public class TramitesRegistradosDTO {
    private Long id;
    private TramitesTiposDTO tramitesTiposId;
    private ClienteDTO clienteId;
    private List<ArchivoRelacionadoDTO> archivosRelacionados;
}
