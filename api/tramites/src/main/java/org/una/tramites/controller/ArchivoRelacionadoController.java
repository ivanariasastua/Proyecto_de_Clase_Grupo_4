package org.una.tramites.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.Date;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<ArchivoRelacionado> arcRelacionadoFound = archivoRelacionadoService.findById(id);
            if (arcRelacionadoFound.isPresent()) {
                ArchivoRelacionadoDTO arcRelacionadoDto = MapperUtils.DtoFromEntity(arcRelacionadoFound.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(arcRelacionadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los archivos relacionados", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos_Relacionados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ArchivoRelacionado>> result = archivoRelacionadoService.findAll();
            if (result.isPresent()) {
                List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(archivoRelacionadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene los archivos relacionados al tramite registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos_Relacionados")
    public ResponseEntity<?> findByTramiteRegistrado(@PathVariable(value = "id")Long  id) {
        try{
            Optional<List<ArchivoRelacionado>> result = archivoRelacionadoService.findByTramiteRegistrado(id);
            if(result.isPresent()){
                List<ArchivoRelacionadoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fechaRegistro}")
    @ApiOperation(value = "Obtiene los archivos relacionados al tramite registrado", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos_Relacionados")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fechaRegistro")Date  fechaRegistro) {
        try{
            Optional<List<ArchivoRelacionado>> result = archivoRelacionadoService.findByFechaRegistro(fechaRegistro);
            if(result.isPresent()){
                List<ArchivoRelacionadoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ArchivoRelacionado arcRelacionado) {
        try {
            ArchivoRelacionado arcRelacionadoCreated = archivoRelacionadoService.create(arcRelacionado);
            ArchivoRelacionadoDTO arcRelacionadoDto = MapperUtils.DtoFromEntity(arcRelacionadoCreated, ArchivoRelacionadoDTO.class);
            return new ResponseEntity<>(arcRelacionadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ArchivoRelacionado arcRelacionadoModified) {
        try {
            Optional<ArchivoRelacionado> arcRelacionadoUpdated = archivoRelacionadoService.update(arcRelacionadoModified, id);
            if (arcRelacionadoUpdated.isPresent()) {
                ArchivoRelacionadoDTO arcRelacionadoDto = MapperUtils.DtoFromEntity(arcRelacionadoUpdated.get(), ArchivoRelacionadoDTO.class);
                return new ResponseEntity<>(arcRelacionadoDto, HttpStatus.OK);
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
            archivoRelacionadoService.delete(id);
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
            archivoRelacionadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
