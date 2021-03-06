/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.tramites.services.ITramitesRegistradosService;

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
    @PreAuthorize("hasAuthority('TRA06')")
    ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity(tramitesRegistradosService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite registrado a travez de su identificador unico", response = TramitesRegistradosDTO.class, tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRA05')")
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
    @PreAuthorize("hasAuthority('TAR01')")
    public ResponseEntity<?> create(@RequestBody TramitesRegistradosDTO tramiteRegistrado) {
        try{
            return new ResponseEntity<>(tramitesRegistradosService.create(tramiteRegistrado), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TAR02')")
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
    @PreAuthorize("hasAuthority('TAR05')")
    public @ResponseBody ResponseEntity<?> findByClienteId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramitesRegistradosService.findByClienteId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo_tramite/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites según el tipo", response = TramitesRegistradosDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
  //  @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public @ResponseBody ResponseEntity<?> findByTipoTramiteId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramitesRegistradosService.findByTramiteTipoId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("filtro/{cedula}/{estado}/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene un tramite registrado a de distintos parametros", response = TramitesRegistradosDTO.class,  responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TAR05')")
    public ResponseEntity<?> getByFilter(@PathVariable(value = "cedula")String cedula, @PathVariable(value = "estado")String estado, @PathVariable(value = "inicio")Date inicio, @PathVariable(value = "fin")Date fin){
        try{
            return new ResponseEntity<>(tramitesRegistradosService.getByFilter(cedula, estado, inicio, fin), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*Filtros de la tarea*/
    @GetMapping("findByCedulaCliente/{cedula}")
    @ApiOperation(value = "Obtiene un tramite registrado a traves de la cedula del cliente", response = TramitesRegistradosDTO.class,  responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByCedulaCliete(@PathVariable("cedula")String cedula){
        try{
            return new ResponseEntity<>(tramitesRegistradosService.getByCedulaCliente(cedula), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("findByEstado/{estado}")
    @ApiOperation(value = "Obtiene un tramite registrado a traves del sus estados", response = TramitesRegistradosDTO.class,  responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByEstado(@PathVariable("estado")String estado){
        try{
            return new ResponseEntity<>(tramitesRegistradosService.getByEstado(estado), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("findByFechas/{yfi}/{mfi}/{dfi}/{yff}/{mff}/{dff}")
    @ApiOperation(value = "Obtiene un tramite registrado a traves de su fecha de registro", response = TramitesRegistradosDTO.class,  responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByFechas(
            @PathVariable("yfi")int yfi,
            @PathVariable("mfi")int mfi,
            @PathVariable("dfi")int dfi,
            @PathVariable("yff")int yff,
            @PathVariable("mff")int mff,
            @PathVariable("dff")int dff
    ){
        try{
            return new ResponseEntity<>(tramitesRegistradosService.getByFechas(yfi, mfi, dfi, yff, mff, dff), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
