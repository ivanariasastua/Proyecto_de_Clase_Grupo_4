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
import municipales.tramite.dto.RequisitosDTO;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;

/**
 *
 * @author cordo
 */
public class RequisitosService {

    public Respuesta getAll() {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("requisitos",usuario);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "Error al obtener todos los requisitos");
            }
            List<RequisitosDTO> result = (List<RequisitosDTO>) request.readEntity(new GenericType<List<RequisitosDTO>>() {
            });
            return new Respuesta(true, "Requisitos", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta getUserById(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "Error al obtener los requisitos");
            }
            RequisitosDTO result = (RequisitosDTO) request.readEntity(RequisitosDTO.class);
            return new Respuesta(true, "Requisitos", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta guardarRequisito(RequisitosDTO requisito, Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos/save","/{id}",parametros);
            request.post(requisito);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo guardar el requisito");
            }
            RequisitosDTO result = (RequisitosDTO) request.readEntity(RequisitosDTO.class);
            return new Respuesta(true, "Requisitos", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta modificarRequisito(Long id, RequisitosDTO requisito) {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos", "/{id}", parametros,usuario);
            request.put(requisito);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo modificar el requisito");
            }
            RequisitosDTO result = (RequisitosDTO) request.readEntity(RequisitosDTO.class);
            return new Respuesta(true, "Requisitos", result);
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta deleteRequisito(Long id) {
        try {
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos", "/{id}", parametros,usuario);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "No se pudo eliminar el requisito");
            }
            return new Respuesta(true, "Requisito eliminado exitosamente");
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }

    public Respuesta deleteAll() {
        try {
            Request request = new Request("requisitos");
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "Error al eliminar los requisitos");
            }
            return new Respuesta(true, "Todos los requisitos fueron eliminados con exito");
        } catch (Exception ex) {
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
