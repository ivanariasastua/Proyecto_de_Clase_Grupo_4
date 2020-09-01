
package org.una.tramites.controller;

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
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.services.IRequisitoPresentadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Dios
 */

@RestController
@RequestMapping("/requisitos_presentados")
@Api(tags = {"Requisitos_Presentados"})
public class RequisitoPresentadoController {
    @Autowired
    IRequisitoPresentadoService requisitoPresentadoService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<RequisitoPresentado> repPresentadoFound = requisitoPresentadoService.findById(id);
            if (repPresentadoFound.isPresent()) {
                RequisitoPresentadoDTO reqPresentadoDto = MapperUtils.DtoFromEntity(repPresentadoFound.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(reqPresentadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los requisitos presentados", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos_Presentados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<RequisitoPresentado>> result = requisitoPresentadoService.findAll();
            if (result.isPresent()) {
                List<RequisitoPresentadoDTO> requisitoPresentadoDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene los requisitos presentados del tramite registrado", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos_Presentados")
    public ResponseEntity<?> findByTramiteRegistrado(@PathVariable(value = "id")Long  id) {
        try{
            Optional<List<RequisitoPresentado>> result = requisitoPresentadoService.findByTramiteRegistrado(id);
            if(result.isPresent()){
                List<RequisitoPresentadoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), RequisitoPresentadoDTO.class);
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
    public ResponseEntity<?> create(@RequestBody RequisitoPresentado reqPresentado) {
        try {
            RequisitoPresentado reqPresentadoCreated = requisitoPresentadoService.create(reqPresentado);
            RequisitoPresentadoDTO reqPresentadoDto = MapperUtils.DtoFromEntity(reqPresentadoCreated, RequisitoPresentadoDTO.class);
            return new ResponseEntity<>(reqPresentadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RequisitoPresentado reqPresentadoModified) {
        try {
            Optional<RequisitoPresentado> reqPresentadoUpdated = requisitoPresentadoService.update(reqPresentadoModified, id);
            if (reqPresentadoUpdated.isPresent()) {
                RequisitoPresentadoDTO reqPresentadoDto = MapperUtils.DtoFromEntity(reqPresentadoUpdated.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(reqPresentadoDto, HttpStatus.OK);
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
            requisitoPresentadoService.delete(id);
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
            requisitoPresentadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
