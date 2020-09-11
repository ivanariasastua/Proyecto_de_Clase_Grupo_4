
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

class VariacionDTO {
    
    private Long id;
    private boolean grupo;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private TramiteTipoDTO tramites; 
    private List<RequisitoDTO> requisitos = new ArrayList<>();
}
