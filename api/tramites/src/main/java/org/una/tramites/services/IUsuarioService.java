/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.UsuarioDTO;

/**
 *
 * @author cordo
 */
public interface IUsuarioService {

    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

    //public Optional<Usuario> login(Usuario usuario); 
    
    public Optional<List<UsuarioDTO>> findUsersByDepartamentoId(Long id);
    
    public Optional<UsuarioDTO> findJefesDepartemento(Long id);
    
    public Optional<UsuarioDTO> findByCedula(String cedula);

   // public String login(AuthenticationRequest authenticationRequest);
    
   // public AuthenticationResponse login(AuthenticationRequest authenticationRequest); 
}
