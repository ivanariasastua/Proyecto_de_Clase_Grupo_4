
package municipales.tramite.dto;

import java.util.Date;
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
public class ArchivoRelacionadoDTO {
    private Long id;
    private TramiteRegistradoDTO tramiteRegistradoId;
    private String nombre;
    private boolean estado;
    private String rutaArchivo;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    private String etiqueta;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
}
