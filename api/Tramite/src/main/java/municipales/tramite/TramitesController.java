/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipales.tramite;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import municipales.tramite.service.VariacionesService;
import municipales.tramite.service.TramiteTipoService;
import municipales.tramite.dto.VariacionesDTO;
import municipales.tramite.dto.RequisitosDTO;
import municipales.tramite.dto.TramitesTiposDTO;
import municipales.tramite.util.Mensaje;
import municipales.tramite.util.Respuesta;
import municipales.tramite.util.AppContext;

/**
 * FXML Controller class
 *
 * @author Dios
 */
public class TramitesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private TableView<TramitesTiposDTO> tvTramiteTipo;
    
    @FXML
    private TableColumn<TramitesTiposDTO,Long> tcIdTramiteTipo;
    
    @FXML
    private TableColumn<TramitesTiposDTO,String> tcTramiteTipoEstado;
    
    @FXML
    private TableColumn<TramitesTiposDTO,String> tcTramiteTipoFecha;
    
    @FXML
    private TableColumn<TramitesTiposDTO,String> tcTramiteTipoDescripcion;
    
    @FXML
    private TableView<VariacionesDTO> tvVariaciones;
    
    @FXML
    private TableColumn<VariacionesDTO,Long> tcIdVariaciones;
    
    @FXML
    private TableColumn<VariacionesDTO,String> tcDescripcion;
    
    @FXML
    private TableColumn<VariacionesDTO,String> tcEstadoVariaciones;
    
    @FXML
    private TableColumn<VariacionesDTO,Integer> tcVariacionesGrupo;
    
    @FXML
    private TableView<RequisitosDTO> tvRequisitos;
    
    @FXML
    private TableColumn<RequisitosDTO,Long> tcIdRequisitos;
    
    @FXML
    private TableColumn<RequisitosDTO,String> tcRequisitosEstados;
    
    @FXML
    private TableColumn<RequisitosDTO,String> tcRequisitosFechas;
    
    @FXML
    private TableColumn<RequisitosDTO,String> tcRequisitosDescripcion;
    
    @FXML
    private BorderPane bpPrincipal;
    
    private Respuesta respuesta;
    private Mensaje mensaje;
    private TramiteTipoService tramiteTipoService;
    private VariacionesService variacionService;
    private TramitesTiposDTO tramiteSeleccionado;
    private VariacionesDTO variacionSeleccionado;
    private RequisitosDTO requisitoSeleccionado;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tramiteTipoService = new TramiteTipoService();
        variacionService = new VariacionesService();
        variacionSeleccionado = null;
        tramiteSeleccionado = null;
        requisitoSeleccionado = null;
        iniciarTablaTipoTramite();
        iniciarTablaVariaciones();
        iniciarTablaRequisitos();
    }
    
    private Boolean diferenciarIDdeDescripcion(String buscar){
        for(char caracter : buscar.toCharArray()){
            if(Character.isLetter(caracter)){
                return true;
            }
        }
        return false;
    }
    
    public void iniciarTablaTipoTramite(){
        tcIdTramiteTipo.setCellValueFactory(new PropertyValueFactory("id"));
        tcTramiteTipoDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tcTramiteTipoFecha.setCellValueFactory(fecha -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(fecha.getValue().getFechaRegistro()));
            return property;
        });
        tcTramiteTipoEstado.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEstado())
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
    }
    
    public void iniciarTablaVariaciones(){
        tcIdVariaciones.setCellValueFactory(new PropertyValueFactory("id"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tcVariacionesGrupo.setCellValueFactory(new PropertyValueFactory("grupo"));
        tcEstadoVariaciones.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEstado())
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
    }
    
    public void iniciarTablaRequisitos(){
        tcIdRequisitos.setCellValueFactory(new PropertyValueFactory("id"));
        tcRequisitosDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tcRequisitosEstados.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().isEstado())
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
        tcRequisitosFechas.setCellValueFactory(fecha -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(fecha.getValue().getFechaRegistro()));
            return property;
        });
        
    }
    
    @FXML
    void actBuscar(ActionEvent event) {
        if(txtBuscar.getText() != null && !txtBuscar.getText().isEmpty()){
            if(diferenciarIDdeDescripcion(txtBuscar.getText())){
                respuesta = tramiteTipoService.getTipoTramiteByDescripcion(txtBuscar.getText());
            }else{
                respuesta = tramiteTipoService.getTipoTramiteById(Long.parseLong(txtBuscar.getText()));
            }
            if(respuesta.getEstado()){
                tvTramiteTipo.getItems().clear();
                tvTramiteTipo.getItems().addAll((List<TramitesTiposDTO>) respuesta.getResultado("TipoTramites"));
            }else{
                mensaje.show(Alert.AlertType.ERROR, "Busqueda de tipos de trámites", respuesta.getMensaje());
            }
        }else{
            respuesta = tramiteTipoService.getAll();
            if(respuesta.getEstado()){
                tvTramiteTipo.getItems().clear();
                tvTramiteTipo.getItems().addAll((List<TramitesTiposDTO>) respuesta.getResultado("TipoTramites"));
            }else{
                mensaje.show(Alert.AlertType.ERROR, "Busqueda de tipos de trámites", respuesta.getMensaje());
            }
        }
        
    }
    
    @FXML
    void actVerVariacion(ActionEvent event){
        mensaje = new Mensaje();
        if(variacionSeleccionado != null){
            mensaje.showModal(Alert.AlertType.INFORMATION, "Información de la variación", null, variacionSeleccionado.toString());
        }else{
            mensaje.showModal(Alert.AlertType.WARNING, "Variación no selecionada", null, "Por favor, selecionar una variación "+
                             "de la tabla para mostrar su información.");
        }
    }
    
    @FXML
    void actEliminarVariacion(ActionEvent event){
        mensaje = new Mensaje();
        if(variacionSeleccionado!= null){
            if(mensaje.showConfirmation("Borrar variación", null, "¿Esta seguro que desea borrar esta variación?")){
                respuesta = variacionService.deleteVariacion(variacionSeleccionado.getId());
                if(respuesta.getEstado()){
                    tvVariaciones.getItems().remove(variacionSeleccionado);
                    variacionSeleccionado = null;
                }else{
                    mensaje.show(Alert.AlertType.ERROR, "Error al borrar la variación", respuesta.getMensaje());
                }
            }
        }else{
            mensaje.showModal(Alert.AlertType.WARNING, "Variación no selecionada", null, "Por favor, selecionar una variación "+
                              "de la tabla para borrarla.");
        }
    }
    
    @FXML
    void actAgregarVariacion(ActionEvent event) throws IOException {
        bpPrincipal.setMouseTransparent(true);
        AppContext.getInstance().set("Variacion", null);
        App.goView("TramitesVariaciones", 750, 600, true, false);
        bpPrincipal.setMouseTransparent(false);
    }
    
    @FXML
    void actEditarVariacion(ActionEvent event) throws IOException {
        bpPrincipal.setMouseTransparent(true);
        AppContext.getInstance().set("Variacion", variacionSeleccionado);
        App.goView("TramitesVariaciones", 750, 600, true, false);
        bpPrincipal.setMouseTransparent(false);
    }
    
    @FXML
    void actAgregarRequisito(ActionEvent event) {
        
    }
    
    @FXML
    void actEditarRequisito(ActionEvent event) {
        
    }
    
    @FXML
    private void actTablaTramiteTipo(MouseEvent event){
        if(tvTramiteTipo.getSelectionModel().getSelectedItem() != null){
            tramiteSeleccionado = tvTramiteTipo.getSelectionModel().getSelectedItem();
            tvVariaciones.getItems().clear();
            tvVariaciones.getItems().addAll(tramiteSeleccionado.getVariaciones());
            AppContext.getInstance().set("Tipo_Tramite", tramiteSeleccionado);
        }
    }
    
    @FXML
    private void actTablaVariaciones(MouseEvent event){
        if(tvVariaciones.getSelectionModel().getSelectedItem() != null){
            variacionSeleccionado = tvVariaciones.getSelectionModel().getSelectedItem();
            tvRequisitos.getItems().clear();
            tvRequisitos.getItems().addAll(variacionSeleccionado.getRequisitos());
            AppContext.getInstance().set("Variacion", variacionSeleccionado);
        }
    }
}
