/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite.service;

import java.util.HashMap;
import java.util.Map;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
import municipales.tramite.dto.PermisoOtorgadoDTO;
/**
 *
 * @author ivana
 */
public class PermisosOtorgadosService {
    
    public Respuesta guardarPermiso(PermisoOtorgadoDTO permiso, Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos_otorgados/create", "/{id}", parametros);
            request.post(permiso);
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo guardar el permiso");
            PermisoOtorgadoDTO result = (PermisoOtorgadoDTO) request.readEntity(PermisoOtorgadoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta modificarPermiso(PermisoOtorgadoDTO permiso, Long id, Long ID){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            parametros.put("ID", ID);
            Request request = new Request("permisos_otorgados/update", "/{id}/{ID}", parametros);
            request.put(permiso);
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo modificar el permiso");
            PermisoOtorgadoDTO result = (PermisoOtorgadoDTO) request.readEntity(PermisoOtorgadoDTO.class);
            return new Respuesta(true, "Permiso", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
    
    public Respuesta delteById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos_otorgados", "/{id}", parametros);
            request.delete();
            if(request.isError())
                return new Respuesta(false, request.getError(), "No se pudo eliminar el permiso");
            return new Respuesta(true, "Permiso eliminado correctamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se pudo establecer comunicacion con el servidor");
        }
    }
}
