/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class VariacionesDTO {
    
    
    private Long id;
    private boolean grupo;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private TramitesTiposDTO tramites; 
    private List<RequisitosDTO> requisitos = new ArrayList<>();
}
