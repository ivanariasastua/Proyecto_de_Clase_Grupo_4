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
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.Permiso;
import org.una.tramites.services.IPermisoService;
import org.una.tramites.utils.MapperUtils;

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
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Permiso>> result = permisoService.findAll();
            if (result.isPresent()) {
                List<PermisoDTO> permisosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<Permiso> permisoFound = permisoService.findById(id);
            if (permisoFound.isPresent()) {
                PermisoDTO perDto = MapperUtils.DtoFromEntity(permisoFound.get(), PermisoDTO.class);
                return new ResponseEntity<>(perDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Codigo/{codigo}")
    @ApiOperation(value = "Obtiene una lista de los permisos segun el codigo", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "codigo") String codigo) {
        try {
            Optional<List<Permiso>> result = permisoService.findByCodigoAproximate(codigo);
            if (result.isPresent()) {
                List<PermisoDTO> permisosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody PermisoDTO per) {
        if(permisoService.findByCodigo(per.getCodigo()).isPresent()){
            return new ResponseEntity<>("Ya existe ese permiso", HttpStatus.CONFLICT);
        }
        try {
            Permiso perCreated = MapperUtils.EntityFromDto(per, Permiso.class);
            perCreated = permisoService.create(perCreated);
            PermisoDTO perDto = MapperUtils.DtoFromEntity(perCreated, PermisoDTO.class);
            return new ResponseEntity<>(perDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Permiso perModified) {
        try {
            Optional<Permiso> perUpdated = permisoService.update(perModified, id);
            if (perUpdated.isPresent()) {
                PermisoDTO perDto = MapperUtils.DtoFromEntity(perUpdated.get(), PermisoDTO.class);
                return new ResponseEntity<>(perDto, HttpStatus.OK);
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
