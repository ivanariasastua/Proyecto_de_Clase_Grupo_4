/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
import org.una.tramites.dto.TramitesCambioEstadoDTO;
import org.una.tramites.entities.TramitesCambioEstado;
import org.una.tramites.services.ITramitesCambioEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/tramites_cambio_estado")
@Api(tags = {"Tramites_Cambio_Estado"})
public class TramitesCambioEstadoController {

    @Autowired
    private ITramitesCambioEstadoService tramiteService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites cambio Estado", response = TramitesCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites_Cambio_Estado")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    @PreAuthorize("hasAuthority('TAR06')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(tramiteService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite cambio estado a travez de su identificador unico", response = TramitesCambioEstadoDTO.class, tags = "Tramites_Cambio_Estado")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @PreAuthorize("hasAuthority('TAR05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramiteService.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    @PreAuthorize("hasAuthority('TRD01')")
    public ResponseEntity<?> create(@RequestBody TramitesCambioEstadoDTO tramites) {
        try {
            return new ResponseEntity<>(tramiteService.create(tramites),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    @PreAuthorize("hasAuthority('TAR02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramitesCambioEstadoDTO tramiteCambioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramitesCambioEstadoDTO> updated = tramiteService.update(tramiteCambioDTO, id);
                if (updated.isPresent()) {
                    return new ResponseEntity(updated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception ex) {
                return new ResponseEntity(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
