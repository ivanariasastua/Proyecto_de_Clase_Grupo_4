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
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
import municipales.tramite.dto.PermisoDTO;
/**
 *
 * @author ivana
 */
public class PermisosService {
    
    public Respuesta getAll(){
        try{
            Request request = new Request("permisos");
            request.get();
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo obtener los permiso");
            List<PermisoDTO> result = (List<PermisoDTO>) request.readEntity(new GenericType<List<PermisoDTO>>(){});
            return new Respuesta(true, "Permisos");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos", "/{id}", parametros);
            request.get();
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo encontrar el permiso");
            PermisoDTO result = (PermisoDTO) request.readEntity(PermisoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta guardarUsuario(PermisoDTO permiso){
        try{
            Request request = new Request("permisos");
            request.post(permiso);
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo guardar el permiso");
            PermisoDTO result = (PermisoDTO) request.readEntity(PermisoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta modificarPermiso(PermisoDTO permiso, Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos", "/{id}", parametros);
            request.put(permiso);
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo modificar el permiso");
            PermisoDTO result = (PermisoDTO) request.readEntity(PermisoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("permisos");
            request.delete();
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo eliminar los permiso");
            return new Respuesta(true, "Permisos Eliminados correctamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta delteById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos", "/{id}", parametros);
            request.delete();
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo eliminar el permiso");
            return new Respuesta(true, "Permiso eliminado correctamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
}
