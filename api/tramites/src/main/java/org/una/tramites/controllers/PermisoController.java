package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.services.IPermisoService;

/**
 *
 * @author Dios
 */
@RestController
@RequestMapping("/permisos")
@Api(tags = {"Permisos"})
public class PermisoController {
    
    @Autowired
    private IPermisoService permisoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los permisos", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(permisoService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(permisoService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Codigo/{codigo}")
    @ApiOperation(value = "Obtiene una lista de los permisos segun el codigo", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity<>(permisoService.findByCodigoAproximate(codigo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@RequestBody PermisoDTO per) {
        if(permisoService.findByCodigo(per.getCodigo()).isPresent()){
            return new ResponseEntity<>("Ya existe ese permiso", HttpStatus.CONFLICT);
        }
        try {
            return new ResponseEntity<>(permisoService.create(per), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PermisoDTO perModified) {
        try {
            Optional<PermisoDTO> perUpdated = permisoService.update(perModified, id);
            if (perUpdated.isPresent()) {
                return new ResponseEntity<>(perUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            permisoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            permisoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
