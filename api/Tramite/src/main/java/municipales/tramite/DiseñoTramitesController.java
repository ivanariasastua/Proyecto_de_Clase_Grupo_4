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

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class DiseñoTramitesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void acrAgregarVariaciones(ActionEvent event) throws IOException {
        App.goView("VariacionesInfo", 750, 519);
    }

    @FXML
    private void actAgregarRequisitos(ActionEvent event) throws IOException {
        App.goView("RequisitosInfo", 750, 496);
    }
    
}
