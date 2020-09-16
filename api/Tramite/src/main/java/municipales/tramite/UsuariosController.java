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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.dto.PermisoOtorgadoDTO;
import municipales.tramite.dto.AuthenticationResponse;
import municipales.tramite.service.UsuarioService;
import municipales.tramite.service.PermisosService;
import municipales.tramite.service.PermisosOtorgadosService;
import municipales.tramite.util.Respuesta;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.AppContext;
import municipales.tramite.dto.PermisoDTO;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
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
    private PermisosOtorgadosService perOtorService;
    private UsuarioDTO select;
    private List<PermisoDTO> todos = new ArrayList<>();
    private List<PermisoDTO> especificos = new ArrayList<>();
    private List<PermisoOtorgadoDTO> usuario = new ArrayList<>();
    private List<PermisoOtorgadoDTO> eliminados = new ArrayList<>();
    private List<PermisoOtorgadoDTO> agregados = new ArrayList<>();
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtCed;
    @FXML
    private TextField txtPassAct;
    @FXML
    private TextField txtPassCon;
    @FXML
    private TextField txtNueva;
    private final String pregunta = "Ingrese una cedula para buscar usuarios con una cedula similar o igual al ingresado\nDeje el campo de texto en blanco\npara mostrar todos los usuarios\nFinalmente solo pulse el boton 'Buscar'";

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new UsuarioService();
        perOtorService = new PermisosOtorgadosService();
        mensaje = new Mensaje();
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
        App.goView("UsuariosInfo", 758, 400 ,true,false);
    }

    @FXML
    private void accionTabla(MouseEvent event) {
        if(tvUsuarios.getSelectionModel().getSelectedItem() != null){
            especificos.clear();
            usuario.clear();
            eliminados.clear();
            agregados.clear();
            select = tvUsuarios.getSelectionModel().getSelectedItem();
            tvPermisos.getItems().clear();
            usuario = select.getPermisos();
            txtCed.setText(select.getCedula());
            txtUser.setText(select.getNombreCompleto());
            tvPermisos.getItems().addAll(usuario);
            boolean add;
            for(PermisoDTO cbper : todos){
                add = true;
                for(PermisoOtorgadoDTO per : select.getPermisos()){
                    if(per.getPermiso().getId().equals(cbper.getId())){
                        add = false;
                    }
                }
                if(add){
                    especificos.add(cbper);
                }
            }
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
    private void accionModificar(ActionEvent event) throws IOException {
        if(select != null){
            AppContext.getInstance().set("usuSelect", select);
            App.goView("UsuariosInfo", 758, 400 ,true,false);
            AppContext.getInstance().set("usuSelect", null);
        }
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
        mensaje.show(Alert.AlertType.INFORMATION, "Busqueda", pregunta);
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
            agregados.add(po);
            tvPermisos.getItems().add(po);
            tvPermisos.refresh();
        }
    }

    @FXML
    private void accionGuardarPermisos(ActionEvent event) {
        int errores = 0, correctos = 0;
        List<PermisoOtorgadoDTO> registrados = new ArrayList<>();
        if(!agregados.isEmpty()){
            Respuesta res;
            for(PermisoOtorgadoDTO per : agregados){
                res = perOtorService.guardarPermiso(per, select.getId());
                if(!res.getEstado()){
                    mensaje.show(Alert.AlertType.INFORMATION, "Guardar permisos", res.getMensaje());
                    errores++;
                }else{
                    correctos++;
                    registrados.add((PermisoOtorgadoDTO) res.getResultado("Permiso"));
                }
            }
            mensaje.show(Alert.AlertType.INFORMATION, "Guardar permiso", "Se registro: "+correctos+": correctos y "+errores+": no se pudieron resgistrar");
            agregados.clear();
            select.getPermisos().addAll(registrados);
            tvPermisos.getItems().clear();
            tvPermisos.getItems().addAll(select.getPermisos());
            tvUsuarios.refresh();
        }else{
            mensaje.show(Alert.AlertType.WARNING, "Guardar Permisos", "No hay permisos que agregar");
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        boolean borrar = false;
        if(tvPermisos.getSelectionModel().getSelectedItems() != null){
            if(!agregados.isEmpty()){
                borrar = mensaje.showConfirmation("Eliminar permisos", null, "Se perderan los cambios que no ha guardado, ¿Desea seguir?");
            }
            if(agregados.isEmpty() || borrar){
                int errores = 0, correctos = 0; 
                List<PermisoOtorgadoDTO> eliminar = List.copyOf(tvPermisos.getSelectionModel().getSelectedItems());
                Respuesta res;
                for(PermisoOtorgadoDTO delete : eliminar){
                    if(delete.getId() > 0){
                        res = perOtorService.delteById(delete.getId());
                        if(res.getEstado()){
                            select.getPermisos().remove(delete);
                            tvPermisos.getItems().remove(delete);
                            correctos++;
                        }else{
                            errores++;
                        }
                    }
                }
                mensaje.show(Alert.AlertType.INFORMATION, "Eliminar permisos", "Se eliminaron "+correctos+": correctamente y "+errores+": no pudieron eliminarse");
                tvPermisos.refresh();
                tvUsuarios.refresh();
            }
        }else{
            mensaje.show(Alert.AlertType.WARNING, "Eliminar permisos", "No hay datos seleccionados");
        }
        
    }

    @FXML
    private void accionInactivar(ActionEvent event) {
        if(tvPermisos.getSelectionModel().getSelectedItems() != null){
            if(mensaje.showConfirmation("Cambiar estado", null, "¿Seguro desea hacer estos cambios?")){
                boolean estado;
                int correctos = 0, errores = 0;
                List<PermisoOtorgadoDTO> inactivar = List.copyOf(tvPermisos.getSelectionModel().getSelectedItems());
                Respuesta res;
                for(PermisoOtorgadoDTO per : inactivar){
                    estado = per.isEstado();
                    per.setEstado(!estado);
                    res = perOtorService.modificarPermiso(per, per.getId(), select.getId());
                    if(res.getEstado()){
                        per = (PermisoOtorgadoDTO) res.getResultado("Permiso");
                        correctos++;
                    }else{
                        errores++;
                    }
                }
                for(PermisoOtorgadoDTO per : select.getPermisos()){
                    for(PermisoOtorgadoDTO per2 : inactivar){
                        if(per.getId().equals(per2.getId())){
                            per.setEstado(per2.isEstado());
                        }
                    }
                }
                tvPermisos.refresh();
            }
        }
    }

    @FXML
    private void accionChangePassword(ActionEvent event) {
        if(select != null && validarPassword()){
            if(txtPassAct.getText().equals(txtPassCon.getText())){
                Respuesta res = service.validarPassword(select.getCedula(), txtPassAct.getText());
                if(res.getEstado()){
                    res = service.modificarUsuario(select.getId(), txtNueva.getText(), 1, select);
                    if(res.getEstado()){
                        mensaje.show(Alert.AlertType.INFORMATION, "Cambiar contraseña", "Cambiar contrasena: cambio exitoso");
                        txtPassAct.clear();
                        txtPassCon.clear();
                        txtNueva.clear();
                    }else{
                        mensaje.show(Alert.AlertType.WARNING, "Cambiar contraseña", res.getMensaje());
                    }
                }else{
                    mensaje.show(Alert.AlertType.WARNING, "Cambiar contraseña", res.getMensaje());
                }
            }
        }
    }
    
    public Boolean validarPassword(){
        String error = "";
        if(txtPassAct.getText() == null || txtPassAct.getText().isEmpty())
            error = "No ingreso la contraseña actual\n";
        if(txtPassCon.getText() == null || txtPassCon.getText().isEmpty())
            error += "No confirmo la contraseña\n";
        if(txtNueva.getText() == null || txtNueva.getText().isEmpty())
            error += "No ingreso una nueva contraseña";
        if(error.isEmpty()){
            return true;
        }else{
            mensaje.show(Alert.AlertType.WARNING, "Cambiar contraseña", error);
            return false;
        }
    }
    
}
