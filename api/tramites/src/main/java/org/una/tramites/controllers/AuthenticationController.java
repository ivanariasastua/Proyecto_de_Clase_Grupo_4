/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.services.IAuthenticationService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author ivana
 */
@RestController
@RequestMapping("/authentication")
@Api(tags = {"Seguridad"})
public class AuthenticationController {
    
    @Autowired
    private IAuthenticationService service;
    
    final String MENSAJE_VERIFICAR_CREDENCIALES = "Debe verificar y proporcionar credenciales correctos para realizar esta acción";
        final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String token = service.login(authenticationRequest).getJwt();
            if (!token.isBlank()) {
                authenticationResponse.setJwt(token);
                Optional<Usuario> user = service.findByCedula(authenticationRequest.getCedula());
                UsuarioDTO userDto = MapperUtils.DtoFromEntity(user.get(), UsuarioDTO.class);
                authenticationResponse.setUsuario(userDto);
                authenticationResponse.setPermisos(userDto.getPermisos());
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
            }
        } catch(UsernameNotFoundException | BadCredentialsException ex){
            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
