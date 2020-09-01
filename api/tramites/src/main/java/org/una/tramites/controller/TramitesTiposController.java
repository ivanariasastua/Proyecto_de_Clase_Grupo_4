/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.tramites.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Api(tags = {"Tramites"})
public class TramitesTiposController {

    @Autowired
    private ITramitesTiposService traService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de tramites", response = TramitesTiposDTO.class, responseContainer = "List", tags = "Tramites")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramitesTipos>> result = traService.findAll();
            if (result.isPresent()) {
                List<TramitesTiposDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), TramitesTiposDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de tramite a travez de su identificador unico", response = TramitesTiposDTO.class, tags = "Tramites")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramitesTipos> tramiteFound = traService.findById(id);
            if (tramiteFound.isPresent()) {
                TramitesTiposDTO tramiteDTO = MapperUtils.DtoFromEntity(tramiteFound.get(), TramitesTiposDTO.class);
                return new ResponseEntity<>(tramiteDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TramitesTipos usuario) {
        try {
            TramitesTipos usuarioCreated = traService.create(usuario);
            TramitesTiposDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, TramitesTiposDTO.class);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramitesTipos traModified) {
        try {
            Optional <TramitesTipos> traUpdated = traService.update(traModified, id);
            if (traUpdated.isPresent()) {
                TramitesTiposDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), TramitesTiposDTO.class);
                return new ResponseEntity<>(traDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            traService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
        try {
            traService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/usuarios_en_departamento/{id}")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<TramitesTipos>> result = traService.findByDepartamentoId(id);
            if (result.isPresent()) {
                List<TramitesTiposDTO> traDto = MapperUtils.DtoListFromEntityList(result.get(), TramitesTiposDTO.class);
                return new ResponseEntity<>(traDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{descripcion}")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion") String descripcion) {
        try {
            Optional<List<TramitesTipos>> result = traService.findByDescripcion(descripcion);
            if (result.isPresent()) {
                List<TramitesTiposDTO> traDto = MapperUtils.DtoListFromEntityList(result.get(), TramitesTiposDTO.class);
                return new ResponseEntity<>(traDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
