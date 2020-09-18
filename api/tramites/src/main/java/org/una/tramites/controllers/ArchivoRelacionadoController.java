package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.Date;
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
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.services.IArchivoRelacionadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Dios
 */

@RestController
@RequestMapping("/archivos_relacionados")
@Api(tags = {"Archivos_Relacionados"})
public class ArchivoRelacionadoController {
    
    @Autowired
    private IArchivoRelacionadoService archivoRelacionadoService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un archivo relacionado a travez de su identificador unico", response = ArchivoRelacionadoDTO.class, tags = "Archivos_Relacionados")
   // @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(archivoRelacionadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los Archivos Relacionados", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos_Relacionados")
   // @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(archivoRelacionadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("tramite_registrado/{id}")
    @ApiOperation(value = "Obtiene los archivos relacionados al tramite registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos_Relacionados")
    public ResponseEntity<?> findByTramiteRegistrado(@PathVariable(value = "id")Long  id) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByTramiteRegistrado(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fechaRegistro}")
    @ApiOperation(value = "Obtiene los archivos relacionados al tramite registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos_Relacionados")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fechaRegistro")Date  fechaRegistro) {
        try {
            return new ResponseEntity(archivoRelacionadoService.findByFechaRegistro(fechaRegistro), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo archivo relacionado", response = ArchivoRelacionadoDTO.class, tags = "Archivos_Relacionados")
   // @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ArchivoRelacionadoDTO archivo) {
        try {
            return new ResponseEntity(archivoRelacionadoService.create(archivo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un archivo relacionado a partir de su Id", response = ArchivoRelacionadoDTO.class, tags = "Archivos_Relacionados")
   // @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ArchivoRelacionadoDTO archivoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ArchivoRelacionadoDTO> Updated = archivoRelacionadoService.update(archivoDTO, id);
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
    @ApiOperation(value = "Borra un archivo relacionado por su identificador unico", tags = "Archivos_Relacionados")
  //  @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            archivoRelacionadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Borra todos los archivos relacionados", tags = "Archivos_Relacionados")
  //  @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            archivoRelacionadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
