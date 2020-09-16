/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.AuthenticationResponse;
import municipales.tramite.dto.ParametrosGeneralesDTO;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;

/**
 *
 * @author cordo
 */
public class ParametrodGeneralesService {

    public Respuesta getAll() {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("parametros_generales/get",usuario);
            request.get();
            if (request.isError()) {
                System.out.println("error");
                return new Respuesta(false, request.getError(), "Error al obtener todos los Parametros Generales");
            }
            List<ParametrosGeneralesDTO> result = (List<ParametrosGeneralesDTO>) request.readEntity(new GenericType<List<ParametrosGeneralesDTO>>() {
            });
            return new Respuesta(true, "Parametros_Generales", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta guardarParametros(ParametrosGeneralesDTO parametros) {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("parametros_generales/save", usuario);
            request.post(parametros);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo guardar los Parametros Generales");
            }
            ParametrosGeneralesDTO result = (ParametrosGeneralesDTO) request.readEntity(ParametrosGeneralesDTO.class);
            return new Respuesta(true, "Parametros_Generales", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta modificarParametros(Long id, ParametrosGeneralesDTO parametrosG) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("parametros_generales/editar", "/{id}", parametros, usuario);
            request.put(parametrosG);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo modificar los parametros generales");
            }
            ParametrosGeneralesDTO result = (ParametrosGeneralesDTO) request.readEntity(ParametrosGeneralesDTO.class);
            return new Respuesta(true, "Parametros_Generales", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta deleteParametros(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("parametros_generales", "/{id}", parametros, usuario);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo eliminar el departamento");
            }
            return new Respuesta(true, "Parametros Generales eliminado exitosamente");
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta getByNombre(String nombre) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", nombre);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("parametros_generales/nombre", "/{term}", parametros,usuario);
            request.get();
            if (request.isError()) {
                System.out.println("error");
                return new Respuesta(false, request.getError(), "Error al obtener los departamentos");
            }
            List<ParametrosGeneralesDTO> result = (List<ParametrosGeneralesDTO>) request.readEntity(new GenericType<List<ParametrosGeneralesDTO>>() {
            });
            return new Respuesta(true, "Parametros_Generales", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
