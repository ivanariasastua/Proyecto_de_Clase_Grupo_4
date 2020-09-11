
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

class TramiteRegistradoDTO {
    private Long id;
    private TramiteTipoDTO tramitesTiposId;
    private ClienteDTO clienteId;
    private List<ArchivoRelacionadoDTO> archivosRelacionados = new ArrayList<>();
}
