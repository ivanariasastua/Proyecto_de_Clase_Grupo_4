
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

class ArchivoRelacionadoDTO {
    private Long id;
    private TramiteRegistradoDTO tramiteRegistradoId;
    private String nombre;
    private boolean estado;
    private String rutaArchivo;
    private Date fechaRegistro;
    private String etiqueta;
    private Date fechaModificacion;
}
