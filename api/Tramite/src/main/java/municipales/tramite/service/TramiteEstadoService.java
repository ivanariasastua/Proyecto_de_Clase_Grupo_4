
package municipales.tramite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.TramitesEstadosDTO;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
import municipales.tramite.dto.AuthenticationRequest;
import municipales.tramite.dto.AuthenticationResponse;
import municipales.tramite.util.AppContext;
/**
 *
 * @author Dios
 */
public class TramiteEstadoService {
    
    public Respuesta getAll(){
        try{
            Request request = new Request("tramites_estados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los estados");
            }
            List<TramitesEstadosDTO> result = (List<TramitesEstadosDTO>) request.readEntity(new GenericType<List<TramitesEstadosDTO>>(){});
            return new Respuesta(true, "TramiteEstado",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTramiteEstadoById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_estados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los estados");
            }
            TramitesEstadosDTO result = (TramitesEstadosDTO) request.readEntity(TramitesEstadosDTO.class);
            return new Respuesta(true, "TramiteEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardarEstadoTramite(TramitesEstadosDTO estadoTramite){
        try{
            Request request = new Request("tramites_estados");
            request.post(estadoTramite);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el estado");
            }
            TramitesEstadosDTO result = (TramitesEstadosDTO) request.readEntity(TramitesEstadosDTO.class);
            return new Respuesta(true, "TramiteEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarTramiteEstado(Long id, TramitesEstadosDTO tramiteEstado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_estados", "/{id}", parametros);
            request.put(tramiteEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el estado");
            }
            TramitesEstadosDTO result = (TramitesEstadosDTO) request.readEntity(TramitesEstadosDTO.class);
            return new Respuesta(true, "TramiteEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteTramiteEstado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_estados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el estado");
            }
            return new Respuesta(true, "Estado eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_estados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los estados");
            }
            return new Respuesta(true, "Todos los estados fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
