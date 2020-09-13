
package municipales.tramite.dto;

import java.util.ArrayList;
import java.util.Date;
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
public class TramiteTipoDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
    private DepartamentoDTO departamento;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private List<VariacionDTO> variaciones = new ArrayList<>();
}
