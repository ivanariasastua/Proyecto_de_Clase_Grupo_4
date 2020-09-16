/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import municipales.tramite.dto.ParametrosGeneralesDTO;
import municipales.tramite.service.ParametrodGeneralesService;
import municipales.tramite.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class ParametrosInfoController implements Initializable {
    
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtValor;
    @FXML
    private ComboBox<String> cbxEstado;
    @FXML
    private TextArea txtDescripcion;

    /**
     * Initializes the controller class.
     */
    Mensaje alertas = new Mensaje();
    private ParametrosGeneralesDTO paramDTO = new ParametrosGeneralesDTO();
    private ParametrodGeneralesService parService = new ParametrodGeneralesService();
    
    boolean modificar = false;
    Long id;
    // private Parametr

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList items = FXCollections.observableArrayList("Activo", "Inactivo");
        cbxEstado.setItems(items);
    }
    
    @FXML
    private void actGuardar(ActionEvent event) {
        if (modificar == true) {
            paramDTO.setId(id);
            paramDTO.setDescripcion(txtDescripcion.getText());
            if (cbxEstado.getValue().equals("Activo")) {
                paramDTO.setEstado(true);
            } else {
                paramDTO.setEstado(false);
            }
            
            paramDTO.setNombre(txtNombre.getText());
            paramDTO.setValor(txtValor.getText());
            
            parService.modificarParametros(id, paramDTO);
            alertas.show(Alert.AlertType.INFORMATION, "Parametro General Editado", "Se ha editado correctamente el parametro general");
            App.CerrarVentana(event);
        } else {
            paramDTO.setDescripcion(txtDescripcion.getText());
            if (cbxEstado.getValue().equals("Activo")) {
                paramDTO.setEstado(true);
            } else {
                paramDTO.setEstado(false);
            }
            
            paramDTO.setNombre(txtNombre.getText());
            paramDTO.setValor(txtValor.getText());
            
            parService.guardarParametros(paramDTO);
            alertas.show(Alert.AlertType.INFORMATION, "Parametro General Guardado", "Se ha guardado correctamente el parametro general");
            App.CerrarVentana(event);
        }
    }
    
    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }
    
    public void EditarParametro(ParametrosGeneralesDTO parametro) {
        id = parametro.getId();
        txtNombre.setText(parametro.getNombre());
        if (parametro.isEstado() == true) {
            cbxEstado.setValue("Activo");
        } else {
            cbxEstado.setValue("Inactivo");
        }
        txtDescripcion.setText(parametro.getDescripcion());
        txtValor.setText(parametro.getValor());
        modificar = true;
        
    }
    
}
