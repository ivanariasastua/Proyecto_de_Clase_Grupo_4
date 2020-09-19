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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.services.IUsuarioService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU05')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un usuario a travez de su identificador unico", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String token = usuarioService.login(authenticationRequest).getJwt();
            if (!token.isBlank()) {
                authenticationResponse.setJwt(token);
                Optional<Usuario> user = usuarioService.findByCedula(authenticationRequest.getCedula());
                UsuarioDTO userDto = MapperUtils.DtoFromEntity(user.get(), UsuarioDTO.class);
                authenticationResponse.setUsuario(userDto);
                authenticationResponse.setPermisos(userDto.getPermisos());
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
            }
        } catch(BadCredentialsException ex){
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
    @GetMapping("/cedula/{term}")
    @ApiOperation(value = "Obtiene una lista de usuarios por medio de su cedula", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(usuarioService.findByCedulaAproximate(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de usuarios por medio de su nombre", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(usuarioService.findByNombreCompletoAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("saveUser/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody UsuarioDTO usuario) {
        try {
            return new ResponseEntity(usuarioService.create(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Usuario a partir de su Id", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioDTO, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borra un usario por su identificador unico", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU006')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Borra todos los usuario", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU07')")
    public ResponseEntity<?> deleteAll() {
        try {
            usuarioService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios_en_departamento/{id}")
    @ApiOperation(value = "Obtiene una lista de usuarios segun el departamento donde se desempeña", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findUsersByDepartamentoId(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/jefe/{id}")
//    @ApiOperation(value = "Obtiene una lista de los jefes de los departementos", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findJefeByDepartemento(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<Usuario> result = usuarioService.findJefesDepartemento(id);
//            if (result.isPresent()) {
//                UsuarioDTO userDto = MapperUtils.DtoFromEntity(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(userDto, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
