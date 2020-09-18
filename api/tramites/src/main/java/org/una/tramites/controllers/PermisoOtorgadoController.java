
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.services.IUsuarioService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Dios
 */

@RestController
@RequestMapping("/permisos_otorgados")
@Api(tags = {"Permisos_Otorgados"})
public class PermisoOtorgadoController {
    
    @Autowired
    private IPermisoOtorgadoService perOtorgadoService;
    
    @Autowired
    private IUsuarioService usuService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(perOtorgadoService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(perOtorgadoService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@PathVariable(value = "id") Long id, @RequestBody PermisoOtorgadoDTO per) {
        try {
            return new ResponseEntity<>(perOtorgadoService.create(per, id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("update/{id}/{ID}")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @PathVariable(value = "ID") Long ID, @RequestBody PermisoOtorgadoDTO perOtorgadoModified) {
        try {
            Optional<PermisoOtorgadoDTO> perOtorgadoUpdated = perOtorgadoService.update(perOtorgadoModified, id, ID);
            if (perOtorgadoUpdated.isPresent()) {
                return new ResponseEntity<>(perOtorgadoUpdated, HttpStatus.OK);
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
            perOtorgadoService.delete(id);
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
            perOtorgadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
