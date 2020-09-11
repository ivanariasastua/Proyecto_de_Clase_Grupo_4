module municipales.tramite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires lombok;
    
    opens municipales.tramite to javafx.fxml;
    exports municipales.tramite;
    
    
}
