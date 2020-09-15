package municipales.tramite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.TramitesTiposDTO;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
import municipales.tramite.dto.AuthenticationRequest;
import municipales.tramite.dto.AuthenticationResponse;
import municipales.tramite.util.AppContext;

/**
 *
 * @author Dios
 */
public class TramiteTipoService {
    
    public Respuesta getAll(){
        try{
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("tramites_tipos/get",usuario);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los tipos de tramites");
            }
            List<TramitesTiposDTO> result = (List<TramitesTiposDTO>) request.readEntity(new GenericType<List<TramitesTiposDTO>>(){});
            return new Respuesta(true, "TipoTramites",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTipoTramiteById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener el tipo de tramite");
            }
            TramitesTiposDTO result = (TramitesTiposDTO) request.readEntity(TramitesTiposDTO.class);
            return new Respuesta(true, "TipoTramites", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTipoTramiteByDepartamento(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos/tipo_departamento", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los tipos de tramites");
            }
            List<TramitesTiposDTO> result = (List<TramitesTiposDTO>) request.readEntity(new GenericType<List<TramitesTiposDTO>>(){});
            return new Respuesta(true, "TipoTramites",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTipoTramiteByDescripcion(String descripcion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("descripcion", descripcion);
            Request request = new Request("tramites_tipos", "/{descripcion}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los tipos de tramites");
            }
            List<TramitesTiposDTO> result = (List<TramitesTiposDTO>) request.readEntity(new GenericType<List<TramitesTiposDTO>>(){});
            return new Respuesta(true, "TipoTramites",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardarTramiteTipo(TramitesTiposDTO tramiteTipo){
        try{
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Request request = new Request("tramites_tipos/save",usuario);
            request.post(tramiteTipo);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el tipo de tramite");
            }
            TramitesTiposDTO result = (TramitesTiposDTO) request.readEntity(TramitesTiposDTO.class);
            return new Respuesta(true, "TipoTramites", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarTramiteTipo(Long id, TramitesTiposDTO tipoTramite){
        try{
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos/editar", "/{id}", parametros,usuario);
            request.put(tipoTramite);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el tipo de tramite");
            }
            TramitesTiposDTO result = (TramitesTiposDTO) request.readEntity(TramitesTiposDTO.class);
            return new Respuesta(true, "TipoTramites", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            AuthenticationResponse usuario = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_tipos", "/{id}", parametros,usuario);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el tipo de tramite");
            }
            return new Respuesta(true, "Tipo de tramite eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_tipos");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los tipos de tramites");
            }
            return new Respuesta(true, "Todos los tipos de tramites fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
