/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.service.DepartamentoService;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class DepartamentosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView tablaDepart;

    private DepartamentoService depService = new DepartamentoService();
    private Respuesta respuesta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        App.goView("DepartamentosInfo", 750, 518);
    }

    @FXML
    void actBuscar(ActionEvent event) {

    }


}
