package municipales.tramite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.util.Request;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try{
            Request request = new Request("usuarios/1");
            request.getMethod();
            if(request.isError()){
                System.out.println(request.getError());
            }else{
                UsuarioDTO user = (UsuarioDTO) request.readEntity(UsuarioDTO.class);
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }
        
        scene = new Scene(loadFXML("LogIn"), 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void goView(String fxml, int ancho, int alto) throws IOException {
        scene = new Scene(loadFXML(fxml), ancho, alto);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    static void CerrarVentana(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
