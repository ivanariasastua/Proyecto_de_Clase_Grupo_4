package org.una.tramites.dto;

import java.sql.Date;
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
    //private TramiteRegistrado tramiteRegistradoId;
    private String nombre;
    private boolean estado;
    private String rutaArchivo;
    private Date fechaRegistro;
    private String etiqueta;
    private Date fechaModificacion;
}
