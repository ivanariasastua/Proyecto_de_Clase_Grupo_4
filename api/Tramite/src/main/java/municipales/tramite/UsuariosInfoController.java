/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import municipales.tramite.util.Respuesta;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.service.UsuarioService;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class UsuariosInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private UsuarioService service;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new UsuarioService();
    }    

    @FXML
    private void actGuardar(ActionEvent event) {
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }
    
}
