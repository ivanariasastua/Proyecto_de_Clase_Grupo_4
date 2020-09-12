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
import org.una.tramites.dto.TramitesRegistradosDTO;
import org.una.tramites.entities.TramitesRegistrados;
import org.una.tramites.services.ITramitesRegistradosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/tramites_registrados")
@Api(tags = {"Tramites_Registrados"})
public class TramitesRegistradosController {

    @Autowired
    private ITramitesRegistradosService tramitesRegistradosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramitesRegistrados>> result = tramitesRegistradosService.findAll();
            if (result.isPresent()) {
                List<TramitesRegistradosDTO> tramitesDTO = MapperUtils.DtoListFromEntityList(result.get(), TramitesRegistradosDTO.class);
                return new ResponseEntity<>(tramitesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite registrado a travez de su identificador unico", response = TramitesRegistradosDTO.class, tags = "Tramites_Registrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramitesRegistrados> tramitesFound = tramitesRegistradosService.findById(id);
            if (tramitesFound.isPresent()) {
                TramitesRegistradosDTO tramitesDTO = MapperUtils.DtoFromEntity(tramitesFound.get(), TramitesRegistradosDTO.class);
                return new ResponseEntity<>(tramitesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TramitesRegistrados tramites) {
        try {
            TramitesRegistrados tramitesCreated = tramitesRegistradosService.create(tramites);
            TramitesRegistradosDTO tramitesDto = MapperUtils.DtoFromEntity(tramitesCreated, TramitesRegistradosDTO.class);
            return new ResponseEntity<>(tramitesDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramitesRegistrados tramitesModified) {
        try {
            Optional<TramitesRegistrados> tramitesUpdated = tramitesRegistradosService.update(tramitesModified, id);
            if (tramitesUpdated.isPresent()) {
                TramitesRegistradosDTO tramitesDto = MapperUtils.DtoFromEntity(tramitesUpdated.get(), TramitesRegistradosDTO.class);
                return new ResponseEntity<>(tramitesDto, HttpStatus.OK);

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
            tramitesRegistradosService.delete(id);
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
            tramitesRegistradosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cliente/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites registrados por el cliente", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    public ResponseEntity<?> findByClienteId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<TramitesRegistrados>> result = tramitesRegistradosService.findByClienteId(id);
            if (result.isPresent()) {
                List<TramitesRegistradosDTO> tramitesRegistradosDTO = MapperUtils.DtoListFromEntityList(result.get(), TramitesRegistradosDTO.class);
                return new ResponseEntity<>(tramitesRegistradosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo_tramite/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites según el tipo", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    public ResponseEntity<?> findByTipoTramiteId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<TramitesRegistrados>> result = tramitesRegistradosService.findByTramiteTipoId(id);
            if (result.isPresent()) {
                List<TramitesRegistradosDTO> tramitesRegistradosDTO = MapperUtils.DtoListFromEntityList(result.get(), TramitesRegistradosDTO.class);
                return new ResponseEntity<>(tramitesRegistradosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
