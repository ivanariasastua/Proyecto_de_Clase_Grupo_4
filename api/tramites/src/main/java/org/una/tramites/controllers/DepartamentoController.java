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
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.jwt.JwtAuthenticationFilter;
import org.una.tramites.services.IDepartamentoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"})
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
  
    @GetMapping("/dep")
    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP04')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(departamentoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DEP04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(departamentoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP01')")
    public ResponseEntity<?> create(@RequestBody DepartamentoDTO departamento) {
        try {
            return new ResponseEntity(departamentoService.create(departamento), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('DEP02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DepartamentoDTO depDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<DepartamentoDTO> Updated = departamentoService.update(depDTO, id);
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DEP03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            departamentoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('DEP03')")
    public ResponseEntity<?> deleteAll() {
        try {
            departamentoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de departamentos por medio de su nombre", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(departamentoService.findByNombreAproximate(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
