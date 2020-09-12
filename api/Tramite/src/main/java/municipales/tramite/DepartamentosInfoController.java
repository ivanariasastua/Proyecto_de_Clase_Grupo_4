/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.service.DepartamentoService;
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
    private DatePicker fechaCreacion;
    @FXML
    private DatePicker fechaModificacion;
    @FXML
    private ComboBox<String> cbxEstado;

    private DepartamentoService departService = new DepartamentoService();
    private DepartamentoDTO departDto = new DepartamentoDTO();

    private Mensaje alertas = new Mensaje();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList items = FXCollections.observableArrayList("Activo", "Inactivo");
        cbxEstado.setItems(items);
    }

    @FXML
    private void actGuardar(ActionEvent event) {
            departDto.setNombre(txtNombre.getText());
//            departDto.setFechaModificacion(Date.valueOf(fechaModificacion.getValue()));
//            departDto.setFechaRegistro(Date.valueOf(fechaCreacion.getValue()));
            // departDto.setEstado(true);
            departService.guardarDepartamento(departDto);

    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }



}
