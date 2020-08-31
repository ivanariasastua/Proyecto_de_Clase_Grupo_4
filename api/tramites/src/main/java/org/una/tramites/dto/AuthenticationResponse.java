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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.dto.UsuarioDTO;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {

    private String jwt;
    private UsuarioDTO usuario;
    private List<PermisoOtorgadoDTO> permisos;

}

