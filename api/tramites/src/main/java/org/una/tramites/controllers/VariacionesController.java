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
import org.una.tramites.dto.VariacionesDTO;
import org.una.tramites.entities.Variaciones;
import org.una.tramites.services.VariacionesServiceImplementation;
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
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las Variaciones", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    public @ResponseBody ResponseEntity<?> findAll(){
        try{
            Optional<List<Variaciones>> result = varService.findAll();
            if(result.isPresent()){
                List<VariacionesDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionesDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una variacion a travez de su identificador unico", response = VariacionesDTO.class, tags = "Variaciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Variaciones> variacionFound = varService.findById(id);
            if (variacionFound.isPresent()) {
                VariacionesDTO variacionDto = MapperUtils.DtoFromEntity(variacionFound.get(), VariacionesDTO.class);
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
    public ResponseEntity<?> create(@RequestBody VariacionesDTO variacion) {
//        try {
            Variaciones varCreated = MapperUtils.EntityFromDto(variacion,Variaciones.class);
            varCreated= varService.create(varCreated);
            VariacionesDTO varDto = MapperUtils.DtoFromEntity(varCreated, VariacionesDTO.class);
            return new ResponseEntity<>(varDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Variaciones varModified) {
        try {
            Optional<Variaciones> varUpdated = varService.update(varModified, id);
            if (varUpdated.isPresent()) {
                VariacionesDTO usuarioDto = MapperUtils.DtoFromEntity(varUpdated.get(), VariacionesDTO.class);
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
            varService.delete(id);
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
            varService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/grupo/{grupo}")
    @ApiOperation(value = "Obtiene una lista de las variaciones por medio de su grupo", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    public ResponseEntity<?> findByGrupo(@PathVariable(value = "grupo") int grupo){
        try{
            Optional<List<Variaciones>> result = varService.findByGrupoContaining(grupo);
            if(result.isPresent()){
                List<VariacionesDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionesDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("descripcion/{descripcion}")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try{
            Optional<List<Variaciones>> result = varService.findByDescripcion(descripcion);
            if(result.isPresent()){
                List<VariacionesDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionesDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
