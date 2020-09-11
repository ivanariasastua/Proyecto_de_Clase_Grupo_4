/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import municipales.tramite.service.UsuarioService;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;


/**
 * FXML Controller class
 *
 * @author cordo
 */
public class LogInController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane apContainer;
    @FXML
    private TextField txtCedula;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private CheckBox cbShow;
    
    private Mensaje alertas;
    
    private UsuarioService service;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertas = new Mensaje();
        service = new UsuarioService();
        cbShow.selectedProperty().addListener( estado -> {
            if(cbShow.isSelected()){
                if(txtPassword.getText() != null && !txtPassword.getText().isEmpty()){
                    cbShow.setText(txtPassword.getText());
                }
            }else{
                cbShow.setText("Mostrar Contraseña");
            }
        });
    }

    @FXML
    private void actIngresar(ActionEvent event) throws IOException {
        if(camposValidos()){
            Respuesta respuesta = service.LogIn(txtCedula.getText(), txtPassword.getText());
            if(respuesta.getEstado()){
                alertas.show(Alert.AlertType.INFORMATION, "Inicio de sesion", "Sesion Inicada correctamente");
                App.goView("Principal",1100, 650);
                App.CerrarVentana(event);
            }else{
                System.out.println(respuesta.getMensaje());
                alertas.show(Alert.AlertType.ERROR, "Inicio de sesion", respuesta.getMensaje());
            }
            
        }
    }

    @Override
    public void initialize() {
    }

    @Override
    public void adjustWidth(double witdh) {
    }

    @Override
    public void adjustHeigth(double height) {
    }
    
    private Boolean camposValidos(){
        String mensaje = "";
        if(txtCedula.getText() == null || txtCedula.getText().isEmpty())
            mensaje = "Campo de texto cedula, esta vacío\n";
        if(txtPassword.getText() == null || txtPassword.getText().isEmpty())
            mensaje += "El campo de contraseña esta vacío";
        if(mensaje.isEmpty())
            return true;
        alertas.show(Alert.AlertType.WARNING, "Inicio de sesion", mensaje);
        return false;
    }

}
