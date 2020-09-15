/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import municipales.tramite.service.VariacionesService;
import municipales.tramite.service.RequisitosService;
import municipales.tramite.dto.VariacionesDTO;
import municipales.tramite.dto.RequisitosDTO;
import municipales.tramite.util.Respuesta;

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
    private TextField txtBuscar;
    
    @FXML
    private TableView<VariacionesDTO> tvVariaciones;
    
    @FXML
    private TableColumn<VariacionesDTO,Long> tcIdVariaciones;
    
    @FXML
    private TableColumn<VariacionesDTO,String> tcDescripcion;
    
    @FXML
    private TableColumn<VariacionesDTO,Boolean> tcEstadoVariaciones;
    
    @FXML
    private TableColumn<VariacionesDTO,Integer> tcVariacionesGrupo;
    
    @FXML
    private TableView<RequisitosDTO> tvRequisitos;
    
    @FXML
    private TableColumn<RequisitosDTO,Long> tcIdRequisitos;
    
    @FXML
    private TableColumn<RequisitosDTO,RequisitosDTO> tcRequisitosVariaciones;
    
    @FXML
    private TableColumn<RequisitosDTO,Boolean> tcRequisitosEstados;
    
    @FXML
    private TableColumn<RequisitosDTO,Date> tcRequisitosFechas;
    
    @FXML
    private TableColumn<RequisitosDTO,String> tcRequisitosDescripcion;
    
    @FXML
    private BorderPane bpPrincipal;
    
    private VariacionesService variacionesService = new VariacionesService();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void actBuscar(ActionEvent event) {
        
    }
    
    @FXML
    void actAgregarVariacion(ActionEvent event) throws IOException {
        bpPrincipal.setMouseTransparent(true);
        App.goView("TramitesVariaciones", 750, 570, true, false);
        bpPrincipal.setMouseTransparent(false);
    }
    
    @FXML
    void actEditarVariacion(ActionEvent event) {
        
    }
    
    @FXML
    void actAgregarRequisito(ActionEvent event) {
        
    }
    
    @FXML
    void actEditarRequisito(ActionEvent event) {
        
    }
    
}
