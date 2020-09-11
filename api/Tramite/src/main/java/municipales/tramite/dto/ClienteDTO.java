
package municipales.tramite.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
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
public class ClienteDTO {
    
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private String telefono;
    private String direccion;
    private boolean estado; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion; 
    private String passwordEncriptado;
    private List<TramiteRegistradoDTO> tramitesRegistrados = new ArrayList<>();
}
