/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.sql.Date;
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
    private Usuario usuarioId;
    private TramitesRegistrados tramitesRegistradosId;
    private TramitesCambioEstado tramitesEstadoId;
    private Date fechaRegistro; 
    
}