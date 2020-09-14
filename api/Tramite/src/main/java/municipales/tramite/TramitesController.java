/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dios
 */
public class TramitesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField txtBuscar;
    
    @FXML
    TableView tvVariaciones;
    
    @FXML
    TableColumn tcIdVariaciones;
    
    @FXML
    TableColumn tcVariaciones;
    
    @FXML
    TableColumn tcEstadoVariaciones;
    
    @FXML
    TableColumn tcVariacionesGrupo;
    
    @FXML
    TableView tvRequisitos;
    
    @FXML
    TableColumn tcIdRequisitos;
    
    @FXML
    TableColumn tcRequisitos;
    
    @FXML
    TableColumn tcRequisitosVariaciones;
    
    @FXML
    TableColumn tcRequisitosEstados;
    
    @FXML
    TableColumn tcRequisitosFechas;
    
    @FXML
    TableColumn tcRequisitosDescripcion;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void actBuscar(ActionEvent event) {
        
    }
    
    @FXML
    void actVerModificar(ActionEvent event) {
        
    }
    
    @FXML
    void actAgregarVariacion(ActionEvent event) {
        
    }
    
    @FXML
    void actAgregarRequisito(ActionEvent event) {
        
    }
    
}
