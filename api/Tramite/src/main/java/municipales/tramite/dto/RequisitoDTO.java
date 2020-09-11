
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
        
class RequisitoDTO {
    
    private Long id;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private VariacionDTO variaciones;
}
