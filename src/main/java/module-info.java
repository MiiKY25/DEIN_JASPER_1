module org.mikel.dein_jasper_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.mikel.dein_jasper_1 to javafx.fxml;
    exports org.mikel.dein_jasper_1;
}