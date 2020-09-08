module municipales.tramite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
      
    opens municipales.tramite to javafx.fxml;
    exports municipales.tramite;
    
}
