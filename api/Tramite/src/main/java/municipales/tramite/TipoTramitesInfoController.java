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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.dto.TramitesTiposDTO;
import municipales.tramite.service.DepartamentoService;
import municipales.tramite.service.TramiteTipoService;
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
        if(cbxDepartamento.getValue()==null || cbxEstado.getValue()==null){
            
        }else{
            System.out.println(cbxDepartamento.getValue().getId());
        //    tramitDto.setDepartamento(cbxDepartamento.getValue());
            if(cbxEstado.getValue()=="Activo"){
                tramitDto.setEstado(true);
            }else{
                tramitDto.setEstado(false);
            }
            tramitDto.setDescripcion(txtDescripcion.getText());
            
            tramitService.guardarTramiteTipo(tramitDto);
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        App.CerrarVentana(event);
    }

    
}
