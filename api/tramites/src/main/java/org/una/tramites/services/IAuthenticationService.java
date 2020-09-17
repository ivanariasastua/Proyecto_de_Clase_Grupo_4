/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Optional;
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author ivana
 */
public interface IAuthenticationService {
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest); 
    public Optional<Usuario> findByCedula(String cedula);
}
