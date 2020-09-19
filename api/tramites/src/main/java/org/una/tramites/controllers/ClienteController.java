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
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.services.IClienteService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/clientes")
@Api(tags = {"Clientes"})
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Clientes", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    @PreAuthorize("hasAuthority('USU05')")
    public @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(clienteService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un cliente a travez de su identificador unico", response = ClienteDTO.class, tags = "Clientes")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cedula/{term}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(clienteService.findByCedulaAproximate(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(clienteService.findByNombreCompletoAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ClienteDTO cliente) {
        try {
            return new ResponseEntity(clienteService.create(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ClienteDTO> Updated = clienteService.update(clienteDTO, id);
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
    @ApiOperation(value = "Borra un cliente por su identificador unico", tags = "Clientes")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Borra todos los clientes", tags = "Clientes")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            clienteService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
