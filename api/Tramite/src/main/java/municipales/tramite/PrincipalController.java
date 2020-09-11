package municipales.tramite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class PrincipalController implements Initializable {

    @FXML
    private VBox vBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void CargarVista(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        loader.load();
        vBox.getChildren().clear();
        vBox.getChildren().add(loader.getRoot());
    }

    @FXML
    private void actDepartamentos(ActionEvent event) throws IOException {
        CargarVista("Departamentos");
    }

    @FXML
    private void actCerrarSesion(ActionEvent event) throws IOException {
        App.goView("LogIn", 900, 600);
        App.CerrarVentana(event);
    }

    @FXML
    private void actTipoTramites(ActionEvent event) throws IOException {
        CargarVista("TipoTramites");
    }

    @FXML
    private void actDisenoTramites(ActionEvent event) throws IOException {
        CargarVista("DiseñoTramites");
    }

    @FXML
    private void actPermisos(ActionEvent event) {
    }

    @FXML
    private void actParametros(ActionEvent event) throws IOException {
        CargarVista("Parametros");
    }

    @FXML
    private void actUsuarios(ActionEvent event) throws IOException {
        CargarVista("Usuarios");
    }
}
