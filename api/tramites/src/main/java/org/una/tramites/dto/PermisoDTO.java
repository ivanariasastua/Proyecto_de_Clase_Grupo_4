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
public class PermisoDTO {
 
    private Long id; 
    private String codigo;
    private String descripcion;
    private Date fechaRegistro; 
    private Date fechaModificacion;
    private boolean estado; 
    
}
