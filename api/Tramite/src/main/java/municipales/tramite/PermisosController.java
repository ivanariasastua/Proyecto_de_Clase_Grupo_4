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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import municipales.tramite.dto.PermisoDTO;
import municipales.tramite.util.Mensaje;
import municipales.tramite.service.PermisosService;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Respuesta;
/**
 * FXML Controller class
 *
 * @author cordo
 */
public class PermisosController implements Initializable {

    @FXML
    private TableView<PermisoDTO> tvPermisos;
    @FXML
    private TableColumn<PermisoDTO, Long> colId;
    @FXML
    private TableColumn<PermisoDTO, String> colCodigo;
    @FXML
    private TableColumn<PermisoDTO, String> colDescripcion;
    @FXML
    private TableColumn<PermisoDTO, String> colEstado;
    @FXML
    private TableColumn<PermisoDTO, String> colRegistrado;
    @FXML
    private TableColumn<PermisoDTO, String> colModificado;
    @FXML
    private TextField txtBuscar;
    private final Mensaje mensaje = new Mensaje();
    private final PermisosService service = new PermisosService();
    private PermisoDTO select = null;
    private final String pregunta = "Ingrese un numero, (ejemplo: 1), para buscar permisos por su id\nIngrese un codigo para buscar permisos con un condigo similar o igual al ingresado\nDeje el campo de texto en blanco\npara mostrar todos los permisos\nFinalmente solo pulse el boton 'Buscar'";
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabla();
    }    

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        AppContext.getInstance().set("permisoFuncion", true);
        App.goView("PermisosInfo", 750, 562,true,false);
    }

    @FXML
    private void accionBuscar(ActionEvent event) {
        Respuesta res;
        if(txtBuscar.getText() != null && !txtBuscar.getText().isEmpty()){
            if(txtBuscar.getText().length() == 1 && Character.isDigit(txtBuscar.getText().charAt(0))){
                res = service.getById(Long.valueOf(txtBuscar.getText()));
                if(res.getEstado()){
                    tvPermisos.getItems().clear();
                    tvPermisos.getItems().add((PermisoDTO)res.getResultado("Permiso"));
                }else{
                    mensaje.show(Alert.AlertType.ERROR, "Buscar permisos", res.getMensaje());
                }
            }else{
                res = service.getByCodigo(txtBuscar.getText());
                if(res.getEstado()){
                    tvPermisos.getItems().clear();
                    tvPermisos.getItems().addAll((List<PermisoDTO>)res.getResultado("Permisos"));
                }else{
                    mensaje.show(Alert.AlertType.ERROR, "Buscar permisos", res.getMensaje());
                }
            }
        }else{
            res = service.getAll();
            if(res.getEstado()){
                tvPermisos.getItems().clear();
                tvPermisos.getItems().addAll((List<PermisoDTO>)res.getResultado("Permisos"));
            }else{
                mensaje.show(Alert.AlertType.ERROR, "Buscar permisos", res.getMensaje());
            }
        }
    }

    @FXML
    private void accionTabla(MouseEvent event) {
        if(tvPermisos.getSelectionModel().getSelectedItem() != null)
            select = tvPermisos.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void accionEditar(ActionEvent event) throws IOException {
        if(select != null){
            AppContext.getInstance().set("permisoFuncion", false);
            AppContext.getInstance().set("perSelect", select);
            App.goView("PermisosInfo", 750, 562,true,false);
        }
    }

    @FXML
    private void accionPregunta(ActionEvent event) {
       mensaje.show(Alert.AlertType.INFORMATION, "Como filtrar", pregunta);
    }
    
    private void initTabla(){
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        colEstado.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEstado())
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
        colRegistrado.setCellValueFactory(data -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(data.getValue().getFechaRegistro()));
            return property;
        });
        colModificado.setCellValueFactory(data -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(data.getValue().getFechaModificacion()));
            return property;
        });
    }
    
}
