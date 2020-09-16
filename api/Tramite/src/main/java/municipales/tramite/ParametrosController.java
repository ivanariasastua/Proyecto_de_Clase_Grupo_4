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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import municipales.tramite.dto.ParametrosGeneralesDTO;
import municipales.tramite.service.ParametrodGeneralesService;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class ParametrosController implements Initializable {

    @FXML
    private TableView tabla;
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    ParametrosGeneralesDTO paramClick = new ParametrosGeneralesDTO();

    ParametrodGeneralesService service = new ParametrodGeneralesService();

    List<ParametrosGeneralesDTO> list = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clickTabla();
        cargarTabla();
    }

    @FXML
    private void actAgregarParametros(ActionEvent event) throws IOException {
        App.goView("ParametrosInfo", 750, 585, true, false);
    }

    @FXML
    private void actBuscar(ActionEvent event) {
    }

    @FXML
    private void actModificar(ActionEvent event) {
    }

    @FXML
    private void actEliminar(ActionEvent event) {
    }

    boolean seleccionado = false;

    public void clickTabla() {
        tabla.setRowFactory(t -> {
            TableRow<ParametrosGeneralesDTO> row = new TableRow();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
                    paramClick = row.getItem();
                    AppContext.getInstance().set("ParametosSeleccionado", paramClick);
                    seleccionado = true;
                }
            });
            return row;
        });

    }

    public String estado(boolean p) {
        if (p == true) {
            return "Activo";
        }
        return "Inactivo";
    }

    public void cargarTabla() {
        tabla.getColumns().clear();
        TableColumn<ParametrosGeneralesDTO, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNombre()));
        TableColumn<ParametrosGeneralesDTO, String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getValor()));
        TableColumn<ParametrosGeneralesDTO, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory((p) -> new SimpleStringProperty(estado(p.getValue().isEstado())));
        TableColumn<ParametrosGeneralesDTO, String> colCreacion = new TableColumn<>("Fecha de creación");
        colCreacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaRegistro())));
        TableColumn<ParametrosGeneralesDTO, String> colModificacion = new TableColumn<>("Última modificación");
        colModificacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaModificacion())));
        TableColumn<ParametrosGeneralesDTO, String> colDescripcion = new TableColumn<>("Descripcion");
        colDescripcion.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getDescripcion()));
        tabla.getColumns().addAll(colNombre, colValor,colEstado, colCreacion, colModificacion, colDescripcion);
        Respuesta res = service.getAll();
        list = (List<ParametrosGeneralesDTO>) res.getResultado("Parametros_Generales");
        if (list != null) {
            ObservableList items = FXCollections.observableArrayList(list);
            tabla.setItems(items);
        } else {
            tabla.getItems().clear();
        }
    }
}
