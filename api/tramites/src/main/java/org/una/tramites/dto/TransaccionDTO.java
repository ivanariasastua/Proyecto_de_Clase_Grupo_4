
package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Dios
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TransaccionDTO {
    private Long id;
    Date fechaRegistro;
    PermisoOtorgado permisoOtorgado;
    String objeto;
    String información;
}
