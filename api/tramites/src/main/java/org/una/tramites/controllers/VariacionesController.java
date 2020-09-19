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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.tramites.dto.VariacionesDTO;
import org.una.tramites.entities.Variaciones;
import org.una.tramites.services.VariacionesServiceImplementation;
import org.una.tramites.services.TramitesTiposServiceImplementation;
import org.una.tramites.utils.MapperUtils;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@RestController
@RequestMapping("/variaciones")
@Api(tags = {"Variaciones"})
public class VariacionesController {

    @Autowired
    private VariacionesServiceImplementation varService;
    
    @Autowired
    private TramitesTiposServiceImplementation tipoTraService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las Variaciones", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('TRA06')")
    public @ResponseBody ResponseEntity<?> findAll(){
        try{
            return new ResponseEntity<>(varService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una variacion a travez de su identificador unico", response = VariacionesDTO.class, tags = "Variaciones")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(varService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRD01')")
    public ResponseEntity<?> create(@PathVariable(value = "id") Long id, @RequestBody VariacionesDTO variacion) {
        try {
            return new ResponseEntity<>(varService.create(variacion, id), HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRD01')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody VariacionesDTO varModified) {
        try {
            Optional<VariacionesDTO> varUpdated = varService.update(varModified, id);
            if (varUpdated.isPresent()) {
                return new ResponseEntity<>(varUpdated, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            varService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> deleteAll() {
        try {
            varService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/grupo/{grupo}")
    @ApiOperation(value = "Obtiene una lista de las variaciones por medio de su grupo", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByGrupo(@PathVariable(value = "grupo") int grupo){
        try{
            return new ResponseEntity<>(varService.findByGrupoContaining(grupo), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("descripcion/{descripcion}")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try{
            return new ResponseEntity<>(varService.findByDescripcion(descripcion), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
