/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.dto.TramitesTiposDTO;
import municipales.tramite.service.TramiteTipoService;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class TipoTramitesController implements Initializable {

    @FXML
    private TableView tabla;
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    private List<TramitesTiposDTO> list = new ArrayList<>();

    private TramiteTipoService tramService;

    private TramitesTiposDTO tramitClick = new TramitesTiposDTO();

    boolean seleccionado = false;
    
    Mensaje alertas = new Mensaje();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tramService = new TramiteTipoService();
        clickTabla();
        cargarTabla();
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        App.goView("TipoTramitesInfo", 750, 512, true, false);
    }

    @FXML
    private void actBuscar(ActionEvent event) {
    }

    @FXML
    private void actModificar(ActionEvent event) {
    }

    @FXML
    private void actEliminar(ActionEvent event) {
        if(seleccionado==true){
            if(alertas.showConfirmation("Eliminar tipo de trámite", null, "Desea eliminar el tipo de trámite")==true){
                tramService.delete(tramitClick.getId());
                cargarTabla();
                alertas.show(Alert.AlertType.INFORMATION, "Tipo de trámite eliminado", "Tipo de trámite eliminado correctamente");
                seleccionado=false;
            }
        }else{
            
        }
    }

    public void clickTabla() {
        tabla.setRowFactory(tv -> {
            TableRow<TramitesTiposDTO> row = new TableRow();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
                    tramitClick = row.getItem();
                    AppContext.getInstance().set("TipoTramiteSelec", tramitClick);
                    seleccionado = true;
                }
            });
            return row;
        });

    }

    public void cargarTabla() {
        tabla.getColumns().clear();
        TableColumn<TramitesTiposDTO, String> colNombre = new TableColumn<>("Departamento");
        colNombre.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getDepartamento().getNombre()));
        TableColumn<TramitesTiposDTO, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().isEstado())));
        TableColumn<TramitesTiposDTO, String> colCreacion = new TableColumn<>("Fecha de creación");
        colCreacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaRegistro())));
        TableColumn<TramitesTiposDTO, String> colModificacion = new TableColumn<>("Última modificación");
        colModificacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaModificacion())));
        TableColumn<TramitesTiposDTO, String> colDescrip = new TableColumn<>("Descripcion");
        colDescrip.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getDescripcion())));
        tabla.getColumns().addAll(/*colNombre,*/ colEstado, colCreacion, colModificacion, colDescrip);
        Respuesta res = tramService.getAll();
        list = (List<TramitesTiposDTO>) res.getResultado("TipoTramites");

        if (list != null) {
            ObservableList items = FXCollections.observableArrayList(list);
            tabla.setItems(items);
        } else {
            tabla.getItems().clear();
        }
    }

}
