
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

public class DepartamentoDTO {
    private Long id;
    private String nombre;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private List<UsuarioDTO> usuarios = new ArrayList<>();
    private List<TramiteTipoDTO> tramitesTipos = new ArrayList<>();
}
