/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.service.DepartamentoService;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class DepartamentosInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cbxEstado;

    private DepartamentoService departService = new DepartamentoService();
    private DepartamentoDTO departDto = new DepartamentoDTO();

    private Mensaje alertas = new Mensaje();

    private boolean modificar = false;
    Long id = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList items = FXCollections.observableArrayList("Activo", "Inactivo");
        cbxEstado.setItems(items);
    }

    @FXML
    private void actGuardar(ActionEvent event) {

        if (modificar == true) {
            departDto.setId(id);
            departDto.setNombre(txtNombre.getText());
            if (cbxEstado.getValue().equals("Activo")) {
                departDto.setEstado(true);
            } else {
                departDto.setEstado(false);
            }
            departService.modificarDepartamento(id, departDto);
            alertas.show(Alert.AlertType.INFORMATION, "Departamento Editado", "Se ha editado correctamente el departamento");
            App.CerrarVentana(event);
        } else {
            if (txtNombre.getText().isEmpty() || txtNombre.getText() == null || cbxEstado.getValue() == null) {
                alertas.show(Alert.AlertType.WARNING, "Campos requeridos", "Los campos son obligatorios");
            } else {
                departDto.setNombre(txtNombre.getText());
                if (cbxEstado.getValue().equals("Activo")) {
                    departDto.setEstado(true);
                } else {
                    departDto.setEstado(false);
                }
                departService.guardarDepartamento(departDto);
                alertas.show(Alert.AlertType.INFORMATION, "Departamento Guardado", "Se ha guardado correctamente el departamento");
                App.CerrarVentana(event);
            }
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }

    public void EditarDepartamento(DepartamentoDTO dep) {
        id = dep.getId();
        txtNombre.setText(dep.getNombre());
        if (dep.isEstado() == true) {
            cbxEstado.setValue("Activo");
        } else {
            cbxEstado.setValue("Inactivo");
        }
        modificar = true;

    }

}
