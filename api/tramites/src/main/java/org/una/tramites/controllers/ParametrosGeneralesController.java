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
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.ParametrosGeneralesDTO;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.ParametrosGenerales;
import org.una.tramites.services.IParametrosGeneralesService;
import org.una.tramites.utils.MapperUtils;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@RestController
@RequestMapping("/parametros_generales")
@Api(tags = {"Parametros_Generales"})
public class ParametrosGeneralesController {
    
    @Autowired
    private IParametrosGeneralesService paramGenService;
    
    @GetMapping("/{nombre}")
    @ApiOperation(value = "Obtiene los Paremetros Generales segun el nombre", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre")String nombre) {
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByNombre(nombre);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{valor}")
    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun el valor que guardan", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor){
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByValor(valor);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun su descripcion", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByDescripcion(descripcion);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametrosGenerales parGen) {
        try {
            Optional<ParametrosGenerales> parGenUpdate = paramGenService.update(parGen, id);
            if (parGenUpdate.isPresent()) {
                ParametrosGeneralesDTO parGenDto = MapperUtils.DtoFromEntity(parGenUpdate.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(parGenDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Parametros Generales", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametrosGenerales>> result = paramGenService.findAll();
            if (result.isPresent()) {
                List<ParametrosGeneralesDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
