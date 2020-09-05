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
import org.una.tramites.dto.NotasDTO;
import org.una.tramites.dto.TramitesTiposDTO;
import org.una.tramites.entities.Notas;
import org.una.tramites.entities.TramitesTipos;
import org.una.tramites.services.NotasServiceImplementation;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/notas")
@Api(tags = {"Notas"})
public class NotasController {

    @Autowired
    private NotasServiceImplementation notasService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = NotasDTO.class, responseContainer = "List", tags = "Notas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Notas>> result = notasService.findAll();
            if (result.isPresent()) {
                List<NotasDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), NotasDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una notas a travez de su identificador unico", response = NotasDTO.class, tags = "Notas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Notas> notasFound = notasService.findById(id);
            if (notasFound.isPresent()) {
                NotasDTO notasDto = MapperUtils.DtoFromEntity(notasFound.get(), NotasDTO.class);
                return new ResponseEntity<>(notasDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Notas notas) {
        try {
            Notas notasCreated = notasService.create(notas);
            NotasDTO notasDto = MapperUtils.DtoFromEntity(notasCreated, NotasDTO.class);
            return new ResponseEntity<>(notasDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Notas notasModified) {
        try {
            Optional<Notas> notasUpdated = notasService.update(notasModified, id);
            if (notasUpdated.isPresent()) {
                NotasDTO notasDto = MapperUtils.DtoFromEntity(notasUpdated.get(), NotasDTO.class);
                return new ResponseEntity<>(notasDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            notasService.delete(id);
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
            notasService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/titulo")
//    public ResponseEntity<?> findByTitulo(@PathVariable(value = "titulo") String titulo) {
//        try {
//            Optional<List<Notas>> result = notasService.findByTitulo(titulo);
//            if (result.isPresent()) {
//                List<NotasDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), NotasDTO.class);
//                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
}
