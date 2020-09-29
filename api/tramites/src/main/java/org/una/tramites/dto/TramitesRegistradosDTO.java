/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author cordo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramitesRegistradosDTO {
    private Long id;
    private TramitesTiposDTO tramitesTiposId;
    private ClienteDTO cliente;
    private List<ArchivoRelacionadoDTO> archivosRelacionados;
    private List<TramitesCambioEstadoDTO> estados;
    private List<RequisitoPresentadoDTO> requisitosPresentados;
}
