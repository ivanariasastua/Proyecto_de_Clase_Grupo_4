/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;
import municipales.tramite.util.AppContext;
import municipales.tramite.dto.VariacionesDTO;
import municipales.tramite.dto.RequisitosDTO;
import municipales.tramite.dto.TramitesTiposDTO;
import municipales.tramite.service.VariacionesService;
import municipales.tramite.util.Mensaje;

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
    private TextField txtDescripcionRequisito;
    
    @FXML
    private ComboBox<String> cbEstado;
    
    @FXML
    private ListView<RequisitosDTO> lvRequisitos;
    
    @FXML 
    private Label lbfechaCreacion;
    
    @FXML
    private Label lbTitulo;
    
    @FXML
    private Label lbfechaTitulo;
    
    @FXML
    private Button btnAccion;
    
    VariacionesDTO variacion;
    VariacionesService variacionService;
    TramitesTiposDTO tramiteTipo;
    Mensaje mensaje;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        variacionService = new VariacionesService();
        variacion = (VariacionesDTO) AppContext.getInstance().get("Variacion");
        tramiteTipo = (TramitesTiposDTO) AppContext.getInstance().get("Tipo_Tramite");
        List<String> estados = new ArrayList();
        estados.add("Activo");
        estados.add("Inactivo");
        cbEstado.setItems((ObservableList<String>) estados);
        lvRequisitos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        modalidad();
    }
    
    public void modalidad(){
        if(variacion != null){
            txtGrupo.setText(String.valueOf(variacion.getGrupo()));
            txtDescripcion.setText(variacion.getDescripcion());
            lbfechaCreacion.setText(variacion.getFechaRegistro().toString());
            cbEstado.getSelectionModel().select(variacion.isEstado()?"Activo":"Inactivo");
            lvRequisitos.setItems((ObservableList<RequisitosDTO>) variacion.getRequisitos());
            lbTitulo.setText("Editar");
            btnAccion.setText("Editar");
        }else{
            cbEstado.setVisible(false);
            lbfechaTitulo.setText(" ");
            lbTitulo.setText("Agregar");
            btnAccion.setText("Agregar");
        }
    }
    
    public void asignarValores(){
        VariacionesDTO aux = new VariacionesDTO();
        aux.setDescripcion(txtDescripcion.getText());
        aux.setGrupo(Integer.parseInt(txtGrupo.getText()));
        aux.setFechaRegistro(new Date());
        aux.setEstado(cbEstado.getSelectionModel().getSelectedItem() == "Activo");
        aux.setRequisitos(lvRequisitos.getItems());
        aux.setTramites(tramiteTipo);
        if(variacion != null){
            aux.setId(variacion.getId());
            variacionService.modificarVariacion(aux.getId(), aux);
        }else{
            variacionService.guardarVariacion(aux);
        }
    }
    
    @FXML
    private void actAgregarEditar(ActionEvent event){
        mensaje = new Mensaje();
        boolean infoCompleta = true;
        String faltante = "Dato(s) faltante(s):\n";
        if(txtGrupo.getText() != null && !txtDescripcion.getText().isEmpty()){
            if(txtDescripcion.getText() != null && !txtDescripcion.getText().isEmpty()){
                if(cbEstado.getSelectionModel().getSelectedItem() != null || variacion == null){
                    if(!lvRequisitos.getSelectionModel().getSelectedItems().isEmpty()){
                        if(infoCompleta){
                            
                        }else{
                            faltante += "Por favor, rellenar todos los campos necesarios.";
                            mensaje.show(Alert.AlertType.ERROR,"Falta de información", faltante);
                        }
                    }else{
                        faltante += "Requisitos.\n";
                        infoCompleta = false;
                    }
                }else{
                    faltante += "Estado.\n";
                    infoCompleta = false;
                }
            }else{
                faltante += "Descripción.\n";
                infoCompleta = false;
            }
        }else{
            faltante += "Grupo de exclusión.\n";
            infoCompleta = false;
        }
    }
    
    @FXML
    private void actAnadirRequisito(ActionEvent event){
        
    }
    
    @FXML
    private void actBorrarRequisito(ActionEvent event){
        
    }
    
}
