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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
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
import municipales.tramite.service.PermisosService;
import municipales.tramite.util.Respuesta;
import municipales.tramite.util.Mensaje;
import municipales.tramite.dto.PermisoDTO;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class UsuariosController implements Initializable {

    @FXML private TableView<UsuarioDTO> tvUsuarios;
    @FXML private TableColumn<UsuarioDTO, Long> colId;
    @FXML private TableColumn<UsuarioDTO, String> colCedula;
    @FXML private TableColumn<UsuarioDTO, String> colNombre;
    @FXML private TableColumn<UsuarioDTO, String> colRegistrado;
    @FXML private TableColumn<UsuarioDTO, String> colModificacion;
    @FXML private TableColumn<UsuarioDTO, String> colEstado;
    @FXML private TableColumn<UsuarioDTO, String> colesJefe;
    @FXML private TextField txtBuscar;
    @FXML private TableView<PermisoOtorgadoDTO> tvPermisos;
    @FXML private TableColumn<PermisoOtorgadoDTO, String> colIdA;
    @FXML private TableColumn<PermisoOtorgadoDTO, String> colCod;
    @FXML private TableColumn<PermisoOtorgadoDTO, String> colDes;
    @FXML private TableColumn<PermisoOtorgadoDTO, String> colEstadoA;
    @FXML private ComboBox<PermisoDTO> cbPermisos;
    private Mensaje mensaje;
    private UsuarioService service; 
    private UsuarioDTO select;
    private List<PermisoDTO> todos = new ArrayList<>();
    private List<PermisoDTO> especificos = new ArrayList<>();
    private List<PermisoOtorgadoDTO> usuario = new ArrayList<>();
    private List<PermisoOtorgadoDTO> eliminados = new ArrayList<>();
    private List<PermisoOtorgadoDTO> agregados = new ArrayList<>();
    private List<PermisoOtorgadoDTO> tabla = new ArrayList<>();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new UsuarioService();
        PermisosService perService = new PermisosService();
        Respuesta res = perService.getAll();
        if(res.getEstado()){
            cbPermisos.getItems().clear();
            todos = (List<PermisoDTO>)res.getResultado("Permisos");
            cbPermisos.getItems().addAll(todos);
        }
        initTabla();
        tvPermisos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    

    @FXML
    private void actAgregarUsuarios(ActionEvent event) throws IOException {
        App.goView("UsuariosInfo", 750, 561,true,false);
    }

    @FXML
    private void accionTabla(MouseEvent event) {
        if(tvUsuarios.getSelectionModel().getSelectedItem() != null){
            especificos.clear();
            usuario.clear();
            eliminados.clear();
            agregados.clear();
            tabla.clear();
            select = tvUsuarios.getSelectionModel().getSelectedItem();
            tvPermisos.getItems().clear();
            usuario = select.getPermisos();
            tabla = usuario;
            tvPermisos.getItems().addAll(tabla);
            select.getPermisos().forEach(po -> {
                todos.stream().filter(p -> (!po.getPermiso().getId().equals(p.getId()))).forEachOrdered(p -> {
                    if(especificos.isEmpty()){
                        especificos.add(p);
                    }else if(especificos.indexOf(p) == -1){
                        especificos.add(p);
                    }
                });
            });
            cbPermisos.getItems().clear();
            cbPermisos.getItems().addAll(especificos);
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
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
    }

    @FXML
    private void accionPregunta(ActionEvent event) {
    }

    @FXML
    private void accionTablaPermiso(MouseEvent event) {
    }

    @FXML
    private void accionAgregarPermiso(ActionEvent event) {
        if(cbPermisos.getSelectionModel().getSelectedItem() != null){
            PermisoDTO per = cbPermisos.getSelectionModel().getSelectedItem();
            cbPermisos.getItems().remove(per);
            PermisoOtorgadoDTO po = new PermisoOtorgadoDTO(Long.valueOf("0"), per, new Date(), true);
            tabla.add(po);
            agregados.add(po);
            tvPermisos.getItems().clear();
            tvPermisos.getItems().addAll(tabla);
        }
    }

    @FXML
    private void accionGuardarPermisos(ActionEvent event) {
        for(PermisoOtorgadoDTO per : eliminados){
            if(per.getId().equals(0))
                eliminados.remove(per);
        }
        for(PermisoOtorgadoDTO per : agregados){
            for(PermisoOtorgadoDTO per2 : eliminados){
                if(per.getPermiso().getCodigo().equals(per2.getPermiso().getCodigo())){
                    agregados.remove(per);
                    eliminados.remove(per2);
                }
            }
        }
        System.out.println("Eliminar\n");
        for(PermisoOtorgadoDTO per : eliminados){
            System.out.println(per.toString());
        }
        System.out.println("Agregar\n");
        for(PermisoOtorgadoDTO per : agregados){
            System.out.println(per.toString());
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        if(tvPermisos.getSelectionModel().getSelectedItems() != null){
            List<PermisoOtorgadoDTO> eliminar = List.copyOf(tvPermisos.getSelectionModel().getSelectedItems());
            for(PermisoOtorgadoDTO delete : eliminar){
                cbPermisos.getItems().add(delete.getPermiso());
                tvPermisos.getItems().remove(delete);
                if(agregados.indexOf(delete) != -1){
                    agregados.remove(delete);
                }
                if(tabla.indexOf(delete) != -1){
                    tabla.remove(delete);
                }
                eliminados.add(delete);
            }
        }
        
    }

    @FXML
    private void accionInactivar(ActionEvent event) {
        if(tvPermisos.getSelectionModel().getSelectedItems() != null){
            boolean estado;
            List<PermisoOtorgadoDTO> inactivar = List.copyOf(tvPermisos.getSelectionModel().getSelectedItems());
            for(PermisoOtorgadoDTO per : inactivar){
                estado = per.isEstado();
                per.setEstado(!estado);
                if(agregados.indexOf(per) == -1){
                    agregados.add(per);
                }else{
                    agregados.get(agregados.indexOf(per)).setEstado(per.isEstado());
                }
            }
            tvPermisos.refresh();
        }
    }
    
}
