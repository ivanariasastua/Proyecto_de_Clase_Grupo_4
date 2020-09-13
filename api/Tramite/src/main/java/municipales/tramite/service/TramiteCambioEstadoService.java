
package municipales.tramite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.TramitesCambioEstadoDTO;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
/**
 *
 * @author Dios
 */
public class TramiteCambioEstadoService {
    
    public Respuesta getAll(){
        try{
            Request request = new Request("tramites_cambio_estado");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los cambios de estados de los tramites");
            }
            List<TramitesCambioEstadoDTO> result = (List<TramitesCambioEstadoDTO>) request.readEntity(new GenericType<List<TramitesCambioEstadoDTO>>(){});
            return new Respuesta(true, "TramitesEstadoCambio",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTramiteCambioEstadoById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_cambio_estado", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener el cambio de estado del tramite");
            }
            TramitesCambioEstadoDTO result = (TramitesCambioEstadoDTO) request.readEntity(TramitesCambioEstadoDTO.class);
            return new Respuesta(true, "TramiteCambioEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardarTramiteCambioEstado(TramitesCambioEstadoDTO tramiteCambioEstado){
        try{
            Request request = new Request("tramites_cambio_estado");
            request.post(tramiteCambioEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el cambio de estado del tramite");
            }
            TramitesCambioEstadoDTO result = (TramitesCambioEstadoDTO) request.readEntity(TramitesCambioEstadoDTO.class);
            return new Respuesta(true, "TramiteCambioEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarTramiteCambioEstado(Long id, TramitesCambioEstadoDTO tramiteCambioEstado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_cambio_estado", "/{id}", parametros);
            request.put(tramiteCambioEstado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el cambio de estado del tramite");
            }
            TramitesCambioEstadoDTO result = (TramitesCambioEstadoDTO) request.readEntity(TramitesCambioEstadoDTO.class);
            return new Respuesta(true, "TramiteCambioEstado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteTramiteRegistrado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_cambio_estado", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el cambio de estado del tramite");
            }
            return new Respuesta(true, "El cambio de estado del tramite se eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_cambio_estado");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los cambios de estado de los tramites");
            }
            return new Respuesta(true, "Todos los cambios de estados de los tramites fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
