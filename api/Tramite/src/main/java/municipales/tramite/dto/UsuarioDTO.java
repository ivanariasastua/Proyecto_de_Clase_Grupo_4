
package municipales.tramite.dto;

import java.util.Date;
import java.util.List;
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
public class UsuarioDTO {
    
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private boolean estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private DepartamentoDTO departamentoId;
    private List<PermisoOtorgadoDTO> permisos;
    private boolean esJefe;
}
