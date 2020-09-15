/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import municipales.tramite.service.UsuarioService;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.util.AppContext;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.service.*;
import municipales.tramite.util.*;
/**
 * FXML Controller class
 *
 * @author cordo
 */
public class UsuariosInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private UsuarioService service;
    private DepartamentoService deptService;
    private UsuarioDTO usuSelect;
    private boolean uso;
    private Mensaje alert;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private ComboBox<DepartamentoDTO> cbDepartamento;
    @FXML
    private TextField txtVer;
    @FXML
    private ComboBox<String> cbEsJefe;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Mensaje();
        deptService = new DepartamentoService();
        Respuesta res = deptService.getAll();
        if(res.getEstado()){
            cbDepartamento.getItems().clear();
            cbDepartamento.getItems().addAll((List<DepartamentoDTO>) res.getResultado("Departamentos"));
        }
        cbEstado.getItems().add("Activo");
        cbEstado.getItems().add("Inactivo");
        cbEsJefe.getItems().add("Sí");
        cbEsJefe.getItems().add("No");
        service = new UsuarioService();
        usuSelect = (UsuarioDTO) AppContext.getInstance().get("usuSelect");
        uso = usuSelect == null;
        if(uso){
            cbEstado.getSelectionModel().select("Activo");
            cbEstado.setMouseTransparent(true);
            cbEsJefe.getSelectionModel().select("No");
        }else{
            cbEstado.getSelectionModel().select(usuSelect.isEstado() ? "Activo" : "Inactivo");
            txtNombre.setText(usuSelect.getNombreCompleto());
            txtCedula.setText(usuSelect.getCedula());
            txtCedula.setDisable(true);
            cbDepartamento.getSelectionModel().select(usuSelect.getDepartamento());
            cbEsJefe.getSelectionModel().select(usuSelect.isEsJefe() ? "Sí" : "No");
        }
    }
   
    @FXML
    private void actGuardar(ActionEvent event) {
        if(validarCampos()){
            Respuesta res; 
            if(uso){
                res = service.guardarUsuario(new UsuarioDTO(
                        Long.valueOf("0"), 
                        txtNombre.getText(), 
                        txtCedula.getText(), 
                        true, 
                        new Date(), 
                        new Date(), 
                        cbDepartamento.getSelectionModel().getSelectedItem(), 
                        null, 
                        cbEsJefe.getSelectionModel().getSelectedItem().equals("Sí")));
                if(res.getEstado()){
                    alert.show(Alert.AlertType.INFORMATION, "Guardar Usuario", "Guardar Usuario: usuario guardado");
                }else{
                    alert.show(Alert.AlertType.ERROR, "Guardar Usuario", res.getMensaje());
                }
            }else{
                usuSelect.setNombreCompleto(txtNombre.getText());
                usuSelect.setEstado(cbEstado.getSelectionModel().getSelectedItem().equals("Activo"));
                usuSelect.setEsJefe(cbEsJefe.getSelectionModel().getSelectedItem().equals("Sí"));
                usuSelect.setFechaModificacion(new Date());
                usuSelect.setDepartamento(cbDepartamento.getSelectionModel().getSelectedItem());
                res = service.modificarUsuario(usuSelect.getId(), null, usuSelect);
                if(res.getEstado()){
                    alert.show(Alert.AlertType.INFORMATION, "Guardar Usuario", "Guardar Usuario: usuario guardado");
                }else{
                    alert.show(Alert.AlertType.ERROR, "Guardar Usuario", res.getMensaje());
                }
                actCancelar(event);
            }
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }

    public Boolean validarCampos(){
        String mensaje = "";
        if(txtNombre.getText().isEmpty() || txtNombre.getText() == null)
            mensaje = "No ha ingresado un nombre\n";
        if(txtCedula.getText().isEmpty() || txtCedula.getText() == null)
            mensaje += "No ha ingresado una cedula\n";
        if(cbDepartamento.getSelectionModel().getSelectedItem() == null)
            mensaje += "No ha seleccionado un departamento\n";
        if(mensaje.isEmpty()){
            return true;
        }else{
            alert.show(Alert.AlertType.WARNING, "Guardar Usario", mensaje);
            return false;
        }
    } 
}
