
package municipales.tramite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import municipales.tramite.dto.ClienteDTO;
import municipales.tramite.util.Request;
import municipales.tramite.util.Respuesta;
/**
 *
 * @author Dios
 */
public class ClienteService {
    
    public Respuesta getAll(){
       try{
           Request request = new Request("clientes");
           request.get();
           if(request.isError()){
               return new Respuesta(false, request.getError(), "Error al obtener los cientes");
           }
           List<ClienteDTO> result = (List<ClienteDTO>) request.readEntity(new GenericType<List<ClienteDTO>>(){});
           return new Respuesta(true, "Clientes", result);
       }catch(Exception ex){
           return new Respuesta(false, ex.toString(), "No se estableció conexión con el servidor");
       } 
    }
    
    public Respuesta getClienteById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("clientes","/{id}",parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(),"Error al obtener el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true, "Cliente", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se estableció conexión con el servidor");
        }
    }
    
    public Respuesta getClienteByCedula(String cedula){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", cedula);
            Request request = new Request("clientes/cedula", "/{term}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los clientes");
            }
            List<ClienteDTO> result = (List<ClienteDTO>) request.readEntity(new GenericType<List<ClienteDTO>>(){});
            return new Respuesta(true, "Cedula_Clientes",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se estableció conexión con la base de datos");
        }
    }
    
    public Respuesta getClienteByNombre(String nombre){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", nombre);
            Request request = new Request("clientes/nombre", "/{term}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los clientes");
            }
            List<ClienteDTO> result = (List<ClienteDTO>) request.readEntity(new GenericType<List<ClienteDTO>>(){});
            return new Respuesta(true, "Nombre_Clientes",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se estableció conexión con la base de datos");
        }
    }
    
    public Respuesta guardarCliente(ClienteDTO cliente){
        try{
            Request request = new Request("clientes");
            request.post(cliente);
            if(request.isError()){
                return new Respuesta(false, request.getError(),"No se pudo guardar el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true,"Cliente",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se estableció conexión con el servidor");
        }
    }
    
    public Respuesta modificarCliente(Long id, ClienteDTO cliente){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("Cliente","/{id}",parametros);
            request.put(cliente);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true,"Cliente",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se establecer conexión con el servidor");
        }
    }
    
    public Respuesta deleteCliente(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("Cliente","{id}",parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false,request.getError(),"No se pudo borrar el cliente");
            }
            return new Respuesta(true, "Cliente eliminado con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No se estableció conexión con el servidor");
        }
    }
    
}
