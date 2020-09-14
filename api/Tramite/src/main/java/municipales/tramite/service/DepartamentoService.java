/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.AuthenticationResponse;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;

/**
 *
 * @author cordo
 */
public class DepartamentoService {

    public Respuesta getAll() {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("departamentos/dep",usuario);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "Error al obtener todos los departamentos");
            }
            List<DepartamentoDTO> result = (List<DepartamentoDTO>) request.readEntity(new GenericType<List<DepartamentoDTO>>(){});
            return new Respuesta(true, "Departamentos", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }


    public Respuesta guardarDepartamento(DepartamentoDTO departamento) {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("departamentos/save", usuario);
            request.post(departamento);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo guardar el departamento");
            }
            DepartamentoDTO result = (DepartamentoDTO) request.readEntity(DepartamentoDTO.class);
            return new Respuesta(true, "Departamentos", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta modificarDepartamento(Long id, DepartamentoDTO departamento) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("departamentos/editar", "/{id}", parametros,usuario);
            request.put(departamento);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo modificar el departamento");
            }
            DepartamentoDTO result = (DepartamentoDTO) request.readEntity(DepartamentoDTO.class);
            return new Respuesta(true, "Departamento", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta deleteDepartamento(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("departamentos", "/{id}", parametros,usuario);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo eliminar el departamento");
            }
            return new Respuesta(true, "Departamento eliminado exitosamente");
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta deleteAll() {
        try {
            Request request = new Request("departamentos");
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "Error al eliminar los departamentos");
            }
            return new Respuesta(true, "Todos los departamentos fueron eliminados con exito");
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByNombre(String nombre){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", nombre);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("departamentos/nombre", "/{term}", parametros,usuario);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los departamentos");
            }
            List<DepartamentoDTO> result = (List<DepartamentoDTO>) request.readEntity(new GenericType<List<DepartamentoDTO>>(){});
            return new Respuesta(true, "Departamentos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
