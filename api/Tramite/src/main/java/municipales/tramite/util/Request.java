package municipales.tramite.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
/**
 *
 * @author Dios
 */
public class Request {
   
    private HttpURLConnection conection;
    private final String apiUrl = "http://localhost:8989/";
    /*
    public static void conectar(){
        try {
        URL url = new URL("http://localhost:8989/usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                + conn.getResponseCode());
        }
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output;
        while ((output = br.readLine()) != null) {
                System.out.println(output);
        }
        conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }*/
    
    public Request(String direccion){
        try {
            URL url = new URL(apiUrl+direccion);
            this.conection = (HttpURLConnection) url.openConnection();
            this.conection.setRequestProperty("Accept", "application/json");
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
        
    }
    
    public Request(String direccion, String parametros, Map<String, Object> valores){
        try{
            URL url = new URL(apiUrl+direccion);
            this.conection = (HttpURLConnection) url.openConnection();
            
            this.conection.setRequestProperty("Accept", "application/json");
        }catch(IOException ex){
            
        }
    }
    
    public void getMethod() throws ProtocolException{
        this.conection.setRequestMethod("GET");
    }
    
    public Boolean isError() throws IOException{
        return this.conection.getResponseCode() != 200;
    }
    
    public String getError() throws IOException{
        if(isError())
            return this.conection.getResponseMessage();
        return "Transaccion existosa";
    }
    
    public Object readEntity(Class<?>[] clase) throws IOException{
        System.out.println(this.conection.getContentType());
        return null;
    }
/*
    public Request(String direccion){
        this.client = ClientBuilder.newClient();
        setDireccion(direccion);
    }
    
    public Request(String direccion, String parametros, Map<String, Object> valores){
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target(apiUrl + direccion).path(parametros).resolveTemplates(valores);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        builder.headers(headers);
    }
    
    public void setDireccion(String direccion){
        this.webTarget = client.target(apiUrl + direccion);
        this.builder = webTarget.request(MediaType.APPLICATION_JSON);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        builder.headers(headers);
    }
    
     public void get() {
        response = builder.get();
    }

    public void post(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.post(entity);
    }

    public void put(Object clazz) {
        Entity<?> entity = Entity.entity(clazz, "application/json; charset=UTF-8");
        response = builder.put(entity);
    }

    public void delete() {
        response = builder.delete();
    }

    public int getStatus() {
        return response.getStatus();
    }
    
    public Boolean isError(){
        return getStatus() != HttpServletResponse.SC_OK;
    }
  
    public String getError() {
        if (response.getStatus() != HttpServletResponse.SC_OK) {
            String mensaje;
            if (response.getMediaType().equals(MediaType.APPLICATION_JSON_TYPE)) {
                mensaje = response.readEntity(String.class);
            } else {
                mensaje = response.getStatusInfo().getReasonPhrase();
            }
            return mensaje;
        }
        return null;
    }
    
    public Object readEntity(Class clazz) {
        return response.readEntity(clazz);
    }
    
    public Object readEntity(GenericType<?> genericType) {
        return response.readEntity(genericType);
    }
*/
}
