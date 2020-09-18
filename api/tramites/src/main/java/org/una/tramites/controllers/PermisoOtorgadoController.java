
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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.entities.Usuario;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.services.IPermisoService;
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
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<PermisoOtorgado>> result = perOtorgadoService.findAll();
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> perOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(perOtorgadosDTO, HttpStatus.OK);
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
            Optional<PermisoOtorgado> perOtorgadoFound = perOtorgadoService.findById(id);
            if (perOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO perOtorDto = MapperUtils.DtoFromEntity(perOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(perOtorDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/create/{id}")
//    @ResponseBody
//    public ResponseEntity<?> create(@PathVariable(value = "id") Long id, @RequestBody PermisoOtorgadoDTO per) {
//        try {
//            Usuario user = usuService.findById(id);
//            PermisoOtorgado entity = MapperUtils.EntityFromDto(per, PermisoOtorgado.class);
//            entity.setUsuario(user);
//            entity = perOtorgadoService.create(entity);
//            PermisoOtorgadoDTO perOtorgadoDto = MapperUtils.DtoFromEntity(entity, PermisoOtorgadoDTO.class);
//            return new ResponseEntity<>(perOtorgadoDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PutMapping("update/{id}/{ID}")
//    @ResponseBody
//    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @PathVariable(value = "ID") Long ID, @RequestBody PermisoOtorgadoDTO perOtorgadoModified) {
//        try {
//            Usuario user = usuService.findById(ID).get();
//            PermisoOtorgado entity = MapperUtils.EntityFromDto(perOtorgadoModified, PermisoOtorgado.class);
//            entity.setUsuario(user);
//            Optional<PermisoOtorgado> perOtorgadoUpdated = perOtorgadoService.update(entity, id);
//            if (perOtorgadoUpdated.isPresent()) {
//                PermisoOtorgadoDTO perOtoDto = MapperUtils.DtoFromEntity(perOtorgadoUpdated.get(), PermisoOtorgadoDTO.class);
//                return new ResponseEntity<>(perOtoDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/{id}")
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
