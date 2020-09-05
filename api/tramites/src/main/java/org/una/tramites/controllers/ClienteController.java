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
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.services.IClienteService;
import org.una.tramites.utils.MapperUtils;

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
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Clientes", response = ClienteDTO.class, responseContainer = "List", tags = "Clientes")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Cliente>> result = clienteService.findAll();
            if (result.isPresent()) {
                List<ClienteDTO> clientesDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un cliente a travez de su identificador unico", response = ClienteDTO.class, tags = "Clientes")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Cliente> clienteFound = clienteService.findById(id);
            if (clienteFound.isPresent()) {
                ClienteDTO clientesDto = MapperUtils.DtoFromEntity(clienteFound.get(), ClienteDTO.class);
                return new ResponseEntity<>(clientesDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cedula/{term}")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Cliente>> result = clienteService.findByCedulaAproximate(term);
            if (result.isPresent()) {
                List<ClienteDTO> clientesDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Cliente>> result = clienteService.findByNombreCompletoAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<ClienteDTO> clientesDTO = MapperUtils.DtoListFromEntityList(result.get(), ClienteDTO.class);
                return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
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
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            Cliente clienteCreated = clienteService.create(cliente);
            ClienteDTO clienteDto = MapperUtils.DtoFromEntity(clienteCreated, ClienteDTO.class);
            return new ResponseEntity<>(clienteDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Cliente clienteModified) {
        try {
            Optional<Cliente> clienteUpdated = clienteService.update(clienteModified, id);
            if (clienteUpdated.isPresent()) {
                ClienteDTO clienteDto = MapperUtils.DtoFromEntity(clienteUpdated.get(), ClienteDTO.class);
                return new ResponseEntity<>(clienteDto, HttpStatus.OK);

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
            clienteService.delete(id);
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
            clienteService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
