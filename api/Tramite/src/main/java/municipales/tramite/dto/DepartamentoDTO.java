
package municipales.tramite.dto;

import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Dios
 */


@Data
@AllArgsConstructor
@NoArgsConstructor 
public class DepartamentoDTO {
    private Long id;
    private String nombre;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
    //private List<UsuarioDTO> usuarios;
    private List<TramitesTiposDTO> tramitesTipos;
    

    @Override
    public String toString(){
        return nombre;
    }
    
}
