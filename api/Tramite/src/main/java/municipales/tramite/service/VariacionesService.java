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
import municipales.tramite.dto.VariacionesDTO;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;

/**
 *
 * @author cordo
 */
public class VariacionesService {
    
     public Respuesta getAll(){
        try{
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("variaciones",usuario);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos las variaciones");
            }
            List<VariacionesDTO> result = (List<VariacionesDTO>) request.readEntity(new GenericType<List<VariacionesDTO>>(){});
            return new Respuesta(true, "Variaciones",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getVariacionById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("variaciones", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener las variaciones");
            }
            VariacionesDTO result = (VariacionesDTO) request.readEntity(VariacionesDTO.class);
            return new Respuesta(true, "Variaciones", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByDescripcion(String descripcion){
        try{
            Map<String,Object> parametros = new HashMap<>();
            parametros.put("descripcion",descripcion);
            Request request = new Request("variaciones/descripcion","/{descripcion}",parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false,request.getError(),"Error al obtener las variaciones");
            }
            List<VariacionesDTO> result = (List<VariacionesDTO>) request.readEntity(new GenericType<List<VariacionesDTO>>(){});
            return new Respuesta(true,"Variaciones",result);
        }catch(Exception ex){
            return new Respuesta(false,ex.toString(),"Error al conectar al servidor");
        }
    }
    
    public Respuesta getByGrupo(int grupo){
        try{
            Map<String,Object> parametros = new HashMap<>();
            parametros.put("grupo",grupo);
            Request request = new Request("variaciones/grupo","/{grupo}",parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false,request.getError(),"Error al obtener las variaciones");
            }
            List<VariacionesDTO> result = (List<VariacionesDTO>) request.readEntity(new GenericType<List<VariacionesDTO>>(){});
            return new Respuesta(true,"Variaciones",result);
        }catch(Exception ex){
            return new Respuesta(false,ex.toString(),"Error al conectar al servidor");
        }
    }
    
    public Respuesta guardarVariacion(VariacionesDTO variacion){
        try{
            Request request = new Request("variaciones/save");
            request.post(variacion);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar la variacion");
            }
            VariacionesDTO result = (VariacionesDTO) request.readEntity(VariacionesDTO.class);
            return new Respuesta(true, "Variaciones", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarVariacion(Long id, VariacionesDTO variacion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("variaciones", "/{id}", parametros,usuario);
            request.put(variacion);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar la variacion");
            }
            VariacionesDTO result = (VariacionesDTO) request.readEntity(VariacionesDTO.class);
            return new Respuesta(true, "Variaciones", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteVariacion(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("variaciones", "/{id}", parametros,usuario);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar la variacion");
            }
            return new Respuesta(true, "Usuario eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("variaciones");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar las variaciones");
            }
            return new Respuesta(true, "Todos las variaciones fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByGrupo(String grupo){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", grupo);
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("variaciones/grupo", "/{term}", parametros,usuario);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los departamentos");
            }
            List<VariacionesDTO> result = (List<VariacionesDTO>) request.readEntity(new GenericType<List<VariacionesDTO>>(){});
            return new Respuesta(true, "Variaciones",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
