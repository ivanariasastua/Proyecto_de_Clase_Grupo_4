
package municipales.tramite.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.ArchivoRelacionadoDTO;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
/**
 *
 * @author Dios
 */
public class ArchivoRelacionadoService {
    
    public Respuesta getAll(){
        try{
            Request request = new Request("archivos_relacionados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los archivos");
            }
            List<ArchivoRelacionadoDTO> result = (List<ArchivoRelacionadoDTO>) request.readEntity(new GenericType<List<ArchivoRelacionadoDTO>>(){});
            return new Respuesta(true, "ArchivosRelacionados",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getArchivoRelacionadoById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener el archivo");
            }
            ArchivoRelacionadoDTO result = (ArchivoRelacionadoDTO) request.readEntity(ArchivoRelacionadoDTO.class);
            return new Respuesta(true, "ArchivoRelacionado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getArchivoRelacionadoByTramiteRegistrado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados/tramite_registrado", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los archivos relacionados del tramite");
            }
            List<ArchivoRelacionadoDTO> result = (List<ArchivoRelacionadoDTO>) request.readEntity(new GenericType<List<ArchivoRelacionadoDTO>>(){});
            return new Respuesta(true, "Archivos_Tramite",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getArchivoRelacionadoByFechaRegistro(Date fechaRegistro){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaRegistro", fechaRegistro);
            Request request = new Request("archivos_relacionados", "/{fechaRegistro}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los archivos según la fecha");
            }
            List<ArchivoRelacionadoDTO> result = (List<ArchivoRelacionadoDTO>) request.readEntity(new GenericType<List<ArchivoRelacionadoDTO>>(){});
            return new Respuesta(true, "Archivos_FechaRegistro",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta guardarArchivoRelacionado(ArchivoRelacionadoDTO archivoRelacionado){
        try{
            Request request = new Request("archivos_relacionados");
            request.post(archivoRelacionado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el archivoRelacionado");
            }
            ArchivoRelacionadoDTO result = (ArchivoRelacionadoDTO) request.readEntity(ArchivoRelacionadoDTO.class);
            return new Respuesta(true, "ArchivoRelacionado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarArchivoRelacionado(Long id, ArchivoRelacionadoDTO archivoRelacionado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados", "/{id}", parametros);
            request.put(archivoRelacionado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el archivoRelacionado");
            }
            ArchivoRelacionadoDTO result = (ArchivoRelacionadoDTO) request.readEntity(ArchivoRelacionadoDTO.class);
            return new Respuesta(true, "ArchivoRelacionado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteArchivoRelacionado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("archivos_relacionados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el archivo");
            }
            return new Respuesta(true, "Archivo eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("archivos_relacionados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los archivos");
            }
            return new Respuesta(true, "Todos los archivos fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
