package org.una.tramites.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Requisitos;
import org.una.tramites.entities.TramitesRegistrados;

/**
 *
 * @author Dios
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class RequisitoPresentadoDTO {
    private Long id;
    private Date fechaRegistro;
    private TramitesRegistrados tramiteRegistradoId;
    private Requisitos requisitoId;
}
