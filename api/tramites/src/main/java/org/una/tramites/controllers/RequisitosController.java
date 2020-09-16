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
import org.una.tramites.dto.RequisitosDTO;
import org.una.tramites.entities.Requisitos;
import org.una.tramites.services.IRequisitosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
@RestController
@RequestMapping("/requisitos")
@Api(tags = {"Requisitos"})
public class RequisitosController {

    @Autowired
    private IRequisitosService reqService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Requisitos", response = RequisitosDTO.class, responseContainer = "List", tags = "Requisitos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Requisitos>> result = reqService.findAll();
            if (result.isPresent()) {
                List<RequisitosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un requisito a travez de su identificador unico", response = RequisitosDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Requisitos> variacionFound = reqService.findById(id);
            if (variacionFound.isPresent()) {
                RequisitosDTO variacionDto = MapperUtils.DtoFromEntity(variacionFound.get(), RequisitosDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RequisitosDTO requisito) {
        try {
            Requisitos varCreated = MapperUtils.EntityFromDto(requisito, Requisitos.class);
            varCreated = reqService.create(varCreated);
            RequisitosDTO varDto = MapperUtils.DtoFromEntity(varCreated, RequisitosDTO.class);
            return new ResponseEntity<>(varDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Requisitos varModified) {
        try {
            Optional<Requisitos> varUpdated = reqService.update(varModified, id);
            if (varUpdated.isPresent()) {
                RequisitosDTO usuarioDto = MapperUtils.DtoFromEntity(varUpdated.get(), RequisitosDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            reqService.delete(id);
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
            reqService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/descripcion")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion") String descripcion) {
        try {
            Optional<List<Requisitos>> result = reqService.findByDescripcion(descripcion);
            if (result.isPresent()) {
                List<RequisitosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
