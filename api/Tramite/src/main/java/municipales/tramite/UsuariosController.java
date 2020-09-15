/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.dto.PermisoOtorgadoDTO;
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
    private TableColumn<UsuarioDTO, String> colRegistrado;
    @FXML
    private TableColumn<UsuarioDTO, String> colModificacion;
    @FXML
    private TableColumn<UsuarioDTO, String> colEstado;
    @FXML
    private TableColumn<UsuarioDTO, String> colesJefe;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<PermisoOtorgadoDTO> tvPermisos;
    @FXML
    private TableColumn<PermisoOtorgadoDTO, String> colIdA;
    @FXML
    private TableColumn<PermisoOtorgadoDTO, String> colCod;
    @FXML
    private TableColumn<PermisoOtorgadoDTO, String> colDes;
    @FXML
    private TableColumn<PermisoOtorgadoDTO, String> colEstadoA;
    @FXML
    private TableColumn<PermisoOtorgadoDTO, Void> colAccionEstado;
    @FXML
    private TableColumn<PermisoOtorgadoDTO, Button> colEliminar;
    private Mensaje mensaje;
    private UsuarioService service; 
    private UsuarioDTO select;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new UsuarioService();
        initTabla();
    }    

    @FXML
    private void actAgregarUsuarios(ActionEvent event) throws IOException {
        App.goView("UsuariosInfo", 750, 561,true,false);
    }

    @FXML
    private void accionTabla(MouseEvent event) {
        if(tvUsuarios.getSelectionModel().getSelectedItem() != null){
            select = tvUsuarios.getSelectionModel().getSelectedItem();
            System.out.println(select.toString());
            tvPermisos.getItems().clear();
            tvPermisos.getItems().addAll(select.getPermisos());
        }
    }

    @FXML
    private void accionBuscar(ActionEvent event) {
        Respuesta respuesta;
        if(txtBuscar.getText() != null && !txtBuscar.getText().isEmpty()){
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
        }else{
            respuesta = service.getAll();
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
        colEstado.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEstado())
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
        colesJefe.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEsJefe())
                estadoString = "Sí";
            else
                estadoString = "No";
            return new ReadOnlyStringWrapper(estadoString);
        });
        colRegistrado.setCellValueFactory(data -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(data.getValue().getFechaRegistro()));
            return property;
        });
        colModificacion.setCellValueFactory(data -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(data.getValue().getFechaModificacion()));
            return property;
        });
        colIdA.setCellValueFactory(new PropertyValueFactory("id"));
        colCod.setCellValueFactory(data -> {
            return new ReadOnlyStringWrapper(data.getValue().getPermiso().getCodigo());
        });
        colDes.setCellValueFactory(data -> {
            return new ReadOnlyStringWrapper(data.getValue().getPermiso().getDescripcion());
        });
        colEstadoA.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEstado())
                estadoString = "Sí";
            else
                estadoString = "No";
            return new ReadOnlyStringWrapper(estadoString);
        });
        colEliminar.setCellFactory(param -> new TableCell<PermisoOtorgadoDTO, Button>() {
            private final Button deleteButton = new Button("Unfriend");

            @Override
            protected void updateItem(Button per, boolean empty) {
                super.updateItem(per, empty);
                if (per == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> tvUsuarios.getItems().remove(param));
            }
        });
    }

    @FXML
    private void accionPregunta(ActionEvent event) {
    }

    @FXML
    private void accionTablaPermiso(MouseEvent event) {
    }
    
}
