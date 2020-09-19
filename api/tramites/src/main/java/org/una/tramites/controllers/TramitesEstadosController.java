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
import org.una.tramites.dto.TramitesEstadosDTO;
import org.una.tramites.entities.TramitesEstados;
import org.una.tramites.services.ITramitesEstadosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/tramites_estados")
@Api(tags = {"Tramites_Estados"})
public class TramitesEstadosController {
    @Autowired
    private ITramitesEstadosService traService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";

    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites estados", response = TramitesEstadosDTO.class, responseContainer = "List", tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TAR06')")
    public @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(traService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un tipo de tramite a travez de su identificador unico", response = TramitesEstadosDTO.class, tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TAR05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(traService.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo estado", response = TramitesEstadosDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    @PreAuthorize("hasAuthority('TRD01')")
    public ResponseEntity<?> create(@RequestBody TramitesEstadosDTO tramiteEstado) {
        try {
            return new ResponseEntity<>(traService.create(tramiteEstado), HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    @PreAuthorize("hasAuthority('TAR02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @ Valid @RequestBody TramitesEstadosDTO tramiteEstado, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<TramitesEstadosDTO> updated = traService.update(tramiteEstado, id);
                if(updated.isPresent()){
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }catch(Exception ex){
                return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            traService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            traService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
