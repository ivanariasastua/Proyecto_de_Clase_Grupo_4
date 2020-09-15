/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.dto.TramitesTiposDTO;
import municipales.tramite.service.DepartamentoService;
import municipales.tramite.service.TramiteTipoService;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class TipoTramitesInfoController implements Initializable {

    @FXML
    private ComboBox<DepartamentoDTO> cbxDepartamento;
    @FXML
    private ComboBox<String> cbxEstado;
    @FXML
    private TextArea txtDescripcion;

    private DepartamentoService depService = new DepartamentoService();
    private List<DepartamentoDTO> departamentos;

    private TramitesTiposDTO tramitDto = new TramitesTiposDTO();
    private TramiteTipoService tramitService = new TramiteTipoService();

    boolean modificar = false;
    Mensaje alertas = new Mensaje();
    Long id = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> estados = FXCollections.observableArrayList("Activo", "Inactivo");
        cbxEstado.setItems(estados);

        Respuesta res = depService.getAll();
        departamentos = (List<DepartamentoDTO>) res.getResultado("Departamentos");
        if (departamentos != null) {
            ObservableList items = FXCollections.observableArrayList(departamentos);
            cbxDepartamento.setItems(items);
        }

    }

    @FXML
    private void actGuardar(ActionEvent event) {
        if (modificar == true) {
            tramitDto.setId(id);
            tramitDto.setDepartamento(cbxDepartamento.getValue());
            if (cbxEstado.getValue() == "Activo") {
                tramitDto.setEstado(true);
            } else {
                tramitDto.setEstado(false);
            }
            tramitDto.setDescripcion(txtDescripcion.getText());
            tramitService.modificarTramiteTipo(id, tramitDto);
            alertas.show(Alert.AlertType.INFORMATION, "Tipo de trámite Guardado", "Se ha guardado correctamente el tipo de trámite");
            App.CerrarVentana(event);
        } else {
            if (cbxDepartamento.getValue() == null || cbxEstado.getValue() == null) {
                alertas.show(Alert.AlertType.WARNING, "Campos requeridos", "Los campos son obligatorios");
            } else {
                tramitDto.setDepartamento(cbxDepartamento.getValue());
                if (cbxEstado.getValue() == "Activo") {
                    tramitDto.setEstado(true);
                } else {
                    tramitDto.setEstado(false);
                }
                tramitDto.setDescripcion(txtDescripcion.getText());

                tramitService.guardarTramiteTipo(tramitDto);
                alertas.show(Alert.AlertType.INFORMATION, "Tipo de trámite Guardado", "Se ha guardado correctamente el tipo de trámite");
                App.CerrarVentana(event);
            }
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }

    void EditarTipoTramite(TramitesTiposDTO tramitClick) {
        id = tramitClick.getId();
        txtDescripcion.setText(tramitClick.getDescripcion());
        if (tramitClick.isEstado() == true) {
            cbxEstado.setValue("Activo");
        } else {
            cbxEstado.setValue("Inactivo");
        }
        cbxDepartamento.setValue(tramitClick.getDepartamento());
        modificar = true;
    }

}
