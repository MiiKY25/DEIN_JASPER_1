module org.mikel.dein_jasper_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.mikel.dein_jasper_1 to javafx.fxml;
    exports org.mikel.dein_jasper_1;
}