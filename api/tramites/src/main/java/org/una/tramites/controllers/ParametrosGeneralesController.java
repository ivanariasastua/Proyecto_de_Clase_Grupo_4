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
import org.una.tramites.dto.ParametrosGeneralesDTO;
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
    
    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene los Paremetros Generales segun el nombre", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByNombreAproximate(term);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<ParametrosGenerales> departamentoFound = paramGenService.findById(id);
            if (departamentoFound.isPresent()) {
                ParametrosGeneralesDTO depDto = MapperUtils.DtoFromEntity(departamentoFound.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(depDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    
//    @GetMapping("/{valor}")
//    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun el valor que guardan", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
//    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor){
//        try{
//            Optional<List<ParametrosGenerales>> result = paramGenService.findByValor(valor);
//            if(result.isPresent()){
//                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
//                return new ResponseEntity<>(resultDto, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch(Exception ex){
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/{descripcion}")
//    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun su descripcion", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
//    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
//        try{
//            Optional<List<ParametrosGenerales>> result = paramGenService.findByDescripcion(descripcion);
//            if(result.isPresent()){
//                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
//                return new ResponseEntity<>(resultDto, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch(Exception ex){
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    @PutMapping("/editar/{id}")
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
    
    @GetMapping("/get")
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
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo departamento", response = ParametrosGeneralesDTO.class, tags = "Parametros_Generales")
    public ResponseEntity<?> create(@RequestBody ParametrosGeneralesDTO parametros) {
        try {
            ParametrosGenerales depart = MapperUtils.EntityFromDto(parametros, ParametrosGenerales.class);
            depart = paramGenService.create(depart);
            ParametrosGeneralesDTO depDto = MapperUtils.DtoFromEntity(depart, ParametrosGeneralesDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            paramGenService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
