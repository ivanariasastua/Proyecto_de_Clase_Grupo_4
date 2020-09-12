package municipales.tramite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.TramiteRegistradoDTO;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
import municipales.tramite.dto.AuthenticationRequest;
import municipales.tramite.dto.AuthenticationResponse;
import municipales.tramite.util.AppContext;
/**
 *
 * @author Dios
 */
public class TramiteRegistradoService {
    
    public Respuesta getAll(){
        try{
            Request request = new Request("tramites_registrados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los tramites");
            }
            List<TramiteRegistradoDTO> result = (List<TramiteRegistradoDTO>) request.readEntity(new GenericType<List<TramiteRegistradoDTO>>(){});
            return new Respuesta(true, "TramitesRegistrados",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTramiteRegistradoById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener el tramite");
            }
            TramiteRegistradoDTO result = (TramiteRegistradoDTO) request.readEntity(TramiteRegistradoDTO.class);
            return new Respuesta(true, "TramiteRegistrado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTramiteRegistradoByCliente(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados/cliente", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los tramites registrados del cliente");
            }
            List<TramiteRegistradoDTO> result = (List<TramiteRegistradoDTO>) request.readEntity(new GenericType<List<TramiteRegistradoDTO>>(){});
            return new Respuesta(true, "Clientes_Tramites",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getTramiteRegistradoByTipo(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados/tipo_tramite", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los tramites");
            }
            List<TramiteRegistradoDTO> result = (List<TramiteRegistradoDTO>) request.readEntity(new GenericType<List<TramiteRegistradoDTO>>(){});
            return new Respuesta(true, "Tipo_Tramites",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardarTramiteRegistrado(TramiteRegistradoDTO tramiteRegistrado){
        try{
            Request request = new Request("tramites_registrados");
            request.post(tramiteRegistrado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el tramite");
            }
            TramiteRegistradoDTO result = (TramiteRegistradoDTO) request.readEntity(TramiteRegistradoDTO.class);
            return new Respuesta(true, "TramiteRegistrado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarTramiteRegistrado(Long id, TramiteRegistradoDTO tramiteRegistrado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados", "/{id}", parametros);
            request.put(tramiteRegistrado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el tramite");
            }
            TramiteRegistradoDTO result = (TramiteRegistradoDTO) request.readEntity(TramiteRegistradoDTO.class);
            return new Respuesta(true, "TramiteRegistrado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteTramiteRegistrado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("tramites_registrados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el tramite");
            }
            return new Respuesta(true, "Tramite eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("tramites_registrados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los tramites");
            }
            return new Respuesta(true, "Todos los tramites fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    
}
