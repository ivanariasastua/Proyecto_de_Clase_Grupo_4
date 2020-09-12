/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.service.UsuarioService;
import municipales.tramite.util.Respuesta;
import municipales.tramite.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class UsuariosController implements Initializable {

    @FXML
    private TableView<UsuarioDTO> tvUsuarios;
    @FXML
    private TableColumn<UsuarioDTO, Long> colId;
    @FXML
    private TableColumn<UsuarioDTO, String> colCedula;
    @FXML
    private TableColumn<UsuarioDTO, String> colNombre;
    @FXML
    private TableColumn<UsuarioDTO, Date> colRegistrado;
    @FXML
    private TableColumn<UsuarioDTO, Date> colModificacion;
    @FXML
    private TableColumn<UsuarioDTO, Boolean> colEstado;
    @FXML
    private TableColumn<UsuarioDTO, Boolean> colesJefe;
    @FXML
    private TextField txtBuscar;
    private Mensaje mensaje;
    private UsuarioService service;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new UsuarioService();
        initTabla();
    }    

    @FXML
    private void actAgregarUsuarios(ActionEvent event) throws IOException {
        App.goView("UsuariosInfo", 750, 561);
    }

    @FXML
    private void accionTabla(MouseEvent event) {
        
    }

    @FXML
    private void accionBuscar(ActionEvent event) {
        if(txtBuscar.getText() != null && !txtBuscar.getText().isEmpty()){
            Respuesta respuesta;
            if(validarBuscar(txtBuscar.getText())){
                respuesta = service.getUsersByCedula(txtBuscar.getText());
            }else{
                respuesta = service.getUsersByNombre(txtBuscar.getText());
            }
            if(respuesta.getEstado()){
                tvUsuarios.getItems().clear();
                tvUsuarios.getItems().addAll((List<UsuarioDTO>)respuesta.getResultado("Usuarios"));
            }else{
                mensaje.show(Alert.AlertType.ERROR, "Busqueda de usuarios", respuesta.getMensaje());
            }
        }
    }

    @FXML
    private void accionModificar(ActionEvent event) {
    }
    
    private Boolean validarBuscar(String buscar){
        for(char caracter : buscar.toCharArray()){
            if(Character.isDigit(caracter)){
                return true;
            }
        }
        return false;
    }
    
    private void initTabla(){
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colCedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        colesJefe.setCellValueFactory(new PropertyValueFactory("esJefe"));
        colRegistrado.setCellValueFactory(new PropertyValueFactory("fechaRegistro"));
        colModificacion.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));
    }
    
}
