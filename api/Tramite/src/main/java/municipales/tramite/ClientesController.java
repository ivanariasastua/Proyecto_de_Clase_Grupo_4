
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Dios
 */
public class ClientesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField tfBuscar;
    
    @FXML
    private TableView tvCliente;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void buscarCliente(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    private void agregarCliente(ActionEvent event) throws IOException {
        
    }
    
}
