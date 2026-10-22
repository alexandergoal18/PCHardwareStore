module com.example.app1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;


    opens com.example.app1 to javafx.fxml;
    exports com.example.app1;
    exports com.example.app1.customer_and_ordering;
    opens com.example.app1.customer_and_ordering to javafx.fxml;
}