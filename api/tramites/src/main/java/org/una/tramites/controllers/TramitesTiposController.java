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
import org.una.tramites.dto.TramitesTiposDTO;
import org.una.tramites.entities.TramitesTipos;
import org.una.tramites.services.ITramitesTiposService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
@RestController
@RequestMapping("/tramites_tipos")
@Api(tags = {"Tramites_Tipos"})
public class TramitesTiposController {

    @Autowired
    private ITramitesTiposService traService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de tramites", response = TramitesTiposDTO.class, responseContainer = "List", tags = "Tramites")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(traService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de tramite a travez de su identificador unico", response = TramitesTiposDTO.class, tags = "Tramites")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(traService.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo tipo de tramite", response = TramitesTiposDTO.class, tags = "Tramites_Tipos")
    public ResponseEntity<?> create(@RequestBody TramitesTiposDTO tramites) {
        try {
            return new ResponseEntity<>(traService.create(tramites), HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramitesTiposDTO tramiteTipoDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<TramitesTiposDTO> updated = traService.update(tramiteTipoDTO, id);
                if(updated.isPresent()){
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                }catch(Exception ex){
                    return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }else{
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            traService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
        try {
            traService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipo_departamento/{id}")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(traService.findByDepartamentoId(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/descripcion/{descripcion}")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion") String descripcion) {
        try {
            return new ResponseEntity<>(traService.findByDescripcion(descripcion), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
