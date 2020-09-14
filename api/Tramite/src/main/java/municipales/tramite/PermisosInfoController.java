/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import municipales.tramite.dto.PermisoDTO;
import municipales.tramite.service.PermisosService;
import municipales.tramite.util.Formato;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class PermisosInfoController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private ComboBox<String> cbEstado;
    private boolean uso;
    private final Mensaje mensaje = new Mensaje();
    private final PermisosService service = new PermisosService();
    PermisoDTO select;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbEstado.getItems().clear();
        cbEstado.getItems().add("Activo");
        cbEstado.getItems().add("Inactivo");
        txtCodigo.setTextFormatter(Formato.getInstance().maxLengthFormat(10));
        txtDescripcion.setTextFormatter(Formato.getInstance().maxLengthFormat(100));
        uso = (boolean) AppContext.getInstance().get("permisoFuncion");
        if(uso){
            cbEstado.setMouseTransparent(true);
            cbEstado.getSelectionModel().select("Activo");
        }else{
            select = (PermisoDTO) AppContext.getInstance().get("perSelect");
            txtCodigo.setText(select.getCodigo());
            txtDescripcion.setText(select.getDescripcion());
            cbEstado.getSelectionModel().select(select.isEstado() ? "Activo" : "Inactivo");
        }
        
    }    

    @FXML
    private void actGuardar(ActionEvent event) { 
        if(validarCampos()){
            PermisoDTO per;
            if(uso){
                per = new PermisoDTO(Long.valueOf("0"), txtCodigo.getText().toUpperCase(), txtDescripcion.getText(), new Date(), new Date(), true);
                Respuesta res = service.guardarPermiso(per);
                if(res.getEstado()){
                    mensaje.show(Alert.AlertType.INFORMATION, "Guardar permiso", "Guardar permiso: Permiso guardado con exito");
                }else{
                    mensaje.show(Alert.AlertType.ERROR, "Guardar permiso", "Guardar permiso: "+res.getMensaje());
                }
            }else{
                per = new PermisoDTO(select.getId(), txtCodigo.getText().toUpperCase(), txtDescripcion.getText(), select.getFechaRegistro(), new Date(), cbEstado.getSelectionModel().getSelectedItem().equals("Activo"));
                Respuesta res = service.modificarPermiso(per, select.getId());
                if(res.getEstado()){
                    mensaje.show(Alert.AlertType.INFORMATION, "Modificar permiso", "Modificar permiso: Permiso modificado con exito");
                }else{
                    mensaje.show(Alert.AlertType.ERROR, "Modificar permiso", "Modificar permiso: "+res.getMensaje());
                }
                AppContext.getInstance().set("perSelect", null);
                actCancelar(event);
            }
        }else{
            mensaje.show(Alert.AlertType.WARNING, "Guardar", "Existen campos vacios");
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }
    
    private Boolean validarCampos(){
        return txtCodigo.getText() != null && !txtCodigo.getText().isEmpty() 
                && txtDescripcion.getText() != null && !txtDescripcion.getText().isEmpty();
    }
    
}
