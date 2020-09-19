/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.una.tramites.dto.ParametrosGeneralesDTO;
import org.una.tramites.services.IParametrosGeneralesService;

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

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(paramGenService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParametrosGeneralesDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ParametrosGeneralesDTO> Updated = paramGenService.update(usuarioDTO, id);
                if (Updated.isPresent()) {
                    return new ResponseEntity(Updated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los Parametros Generales", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU05')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(paramGenService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo Parametro General", response = ParametrosGeneralesDTO.class, tags = "Parametros_Generales")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ParametrosGeneralesDTO usuario) {
        try {
            return new ResponseEntity(paramGenService.create(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            paramGenService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
