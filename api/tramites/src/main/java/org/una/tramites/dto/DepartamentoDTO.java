/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.tramites.dto;

import java.sql.Date;
import java.util.ArrayList;
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
public class DepartamentoDTO {
    
    private Long id;
    private String nombre;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private List<UsuarioDTO> usuarios = new ArrayList<>();
    private List<TramitesTiposDTO> tramitesTipos = new ArrayList<>();
}
