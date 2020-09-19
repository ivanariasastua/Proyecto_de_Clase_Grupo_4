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
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";


    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    public @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity(tramitesRegistradosService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un tramite registrado a travez de su identificador unico", response = TramitesRegistradosDTO.class, tags = "Tramites_Registrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try{
            return new ResponseEntity<>(tramitesRegistradosService.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo tramite", response = TramitesRegistradosDTO.class, tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@RequestBody TramitesRegistradosDTO tramiteRegistrado) {
        try{
            return new ResponseEntity<>(tramitesRegistradosService.create(tramiteRegistrado), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramitesRegistradosDTO tramiteRegistradoDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<TramitesRegistradosDTO> updated = tramitesRegistradosService.update(tramiteRegistradoDTO, id);
                if(updated.isPresent()){
                    return new ResponseEntity(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            }catch(Exception ex){
                return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            }else{
                return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
            }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramitesRegistradosService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramitesRegistradosService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cliente/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites registrados por el cliente", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public @ResponseBody ResponseEntity<?> findByClienteId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramitesRegistradosService.findByClienteId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo_tramite/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites según el tipo", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public @ResponseBody ResponseEntity<?> findByTipoTramiteId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramitesRegistradosService.findByTramiteTipoId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
