/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.TramitesCambioEstado;
import org.una.tramites.entities.TramitesRegistrados;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author cordo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramitesCambioEstadoDTO {
    private Long id;
    private UsuarioDTO usuarioId;
    private TramitesRegistradosDTO tramitesRegistradosId;
    private TramitesCambioEstadoDTO tramitesEstadoId;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    
}
