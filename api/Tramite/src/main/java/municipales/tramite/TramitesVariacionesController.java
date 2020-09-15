/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dios
 */
public class TramitesVariacionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtGrupo;
    
    @FXML
    private TextField txtDescripcion;
    
    @FXML
    private ComboBox cbTipoTramite;
    
    @FXML
    private ComboBox cbEstado;
    
    @FXML
    private ListView lvRequisitos;
    
    @FXML 
    private Label lbfechaCreacion;
    
    @FXML
    private Label lbfechaModificación;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void actAgregarEditar(ActionEvent event) throws IOException {
        
    }
    
}
