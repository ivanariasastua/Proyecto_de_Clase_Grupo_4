/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Data;
import municipales.tramite.dto.DepartamentoDTO;
import municipales.tramite.dto.UsuarioDTO;
import municipales.tramite.service.DepartamentoService;
import municipales.tramite.util.AppContext;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author cordo
 */
public class DepartamentosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView tablaDepart;
    List<DepartamentoDTO> list;

    private DepartamentoService depService = new DepartamentoService();
    private Respuesta respuesta;

    private DepartamentoDTO departClick;
    Mensaje alertas = new Mensaje();
    boolean seleccionado = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        departClick = new DepartamentoDTO();
        clickTabla();
        cargarTabla();

    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        App.goView("DepartamentosInfo", 708, 451, true, false);
    }

    @FXML
    void actBuscar(ActionEvent event) {
        if (txtBuscar.getText() != null && !txtBuscar.getText().isEmpty()) {
            tablaDepart.getColumns().clear();
            TableColumn<DepartamentoDTO, String> colNombre = new TableColumn<>("Nombre");
            colNombre.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNombre()));
            TableColumn<DepartamentoDTO, String> colEstado = new TableColumn<>("Estado");
            colEstado.setCellValueFactory((p) -> new SimpleStringProperty(estado(p.getValue().isEstado())));
            TableColumn<DepartamentoDTO, String> colCreacion = new TableColumn<>("Fecha de creación");
            colCreacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaRegistro())));
            TableColumn<DepartamentoDTO, String> colModificacion = new TableColumn<>("Última modificación");
            colModificacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaModificacion())));
            tablaDepart.getColumns().addAll(colNombre, colEstado, colCreacion, colModificacion);
            Respuesta res = depService.getByNombre(txtBuscar.getText());
            list = (List<DepartamentoDTO>) res.getResultado("Departamentos");

            if (list != null) {
                ObservableList items = FXCollections.observableArrayList(list);
                tablaDepart.setItems(items);
            } else {
                tablaDepart.getItems().clear();
            }
        }
    }

    public void clickTabla() {
        tablaDepart.setRowFactory(tv -> {
            TableRow<DepartamentoDTO> row = new TableRow();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
                    departClick = row.getItem();
                    System.out.println(departClick.getNombre());
                    AppContext.getInstance().set("DepartamentoSeleccionado", departClick);
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
        tablaDepart.getColumns().clear();
        TableColumn<DepartamentoDTO, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getNombre()));
        TableColumn<DepartamentoDTO, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory((p) -> new SimpleStringProperty(estado(p.getValue().isEstado())));
        TableColumn<DepartamentoDTO, String> colCreacion = new TableColumn<>("Fecha de creación");
        colCreacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaRegistro())));
        TableColumn<DepartamentoDTO, String> colModificacion = new TableColumn<>("Última modificación");
        colModificacion.setCellValueFactory((p) -> new SimpleStringProperty(String.valueOf(p.getValue().getFechaModificacion())));
        tablaDepart.getColumns().addAll(colNombre, colEstado, colCreacion, colModificacion);
        Respuesta res = depService.getAll();
        list = (List<DepartamentoDTO>) res.getResultado("Departamentos");

        if (list != null) {
            ObservableList items = FXCollections.observableArrayList(list);
            tablaDepart.setItems(items);
        } else {
            tablaDepart.getItems().clear();
        }
    }

    @FXML
    private void actModificar(ActionEvent event) throws IOException {
        if (seleccionado == true) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("DepartamentosInfo.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            DepartamentosInfoController editar = loader.getController();
            editar.EditarDepartamento(departClick);
        } else {
            alertas.show(Alert.AlertType.WARNING, "Modificar departamento", "Debe seleccionar un departamento");
        }
    }

    @FXML
    private void actEliminar(ActionEvent event) {
        if (seleccionado == true) {
            if (alertas.showConfirmation("Eliminar Departamento", null, "Esta seguro que desea eliminar el departamento de " + departClick.getNombre()) == true) {
                depService.deleteDepartamento(departClick.getId());
                cargarTabla();
                alertas.show(Alert.AlertType.INFORMATION, "Departamento eliminado", "Departamento eliminado correctamente");
                seleccionado = false;
            }
        } else {
            alertas.show(Alert.AlertType.WARNING, "Eliminar departamento", "Debe seleccionar un departamento");
        }

    }

}
