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
        departDto = new DepartamentoDTO(Long.valueOf("0"), "Recursos Humanos", true, new Date(), new Date(), null, null);
        Respuesta res = departService.guardarDepartamento(departDto);
        //Respuesta res = departService.getAll();
        System.out.println("\nMenInt: "+res.getMensajeInterno()+"\nMen: "+res.getMensaje());
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }



}
