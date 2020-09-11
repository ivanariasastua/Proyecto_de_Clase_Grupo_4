package municipales.tramite.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Dios
 */
public class Request {
    public static void conectar(){
        try {
        URL url = new URL("http://localhost:8989/Usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                + conn.getResponseCode());
        }
        //InputStreamReader in = new InputStreamReader(conn.getInputStream());
        //BufferedReader br = new BufferedReader(in);
        String output;
        /*while ((output = br.readLine()) != null) {
                System.out.println(output);
        }*/
        conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }
}
